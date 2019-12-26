package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.TaskAddDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.gwt.common.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

import static com.geekbrains.gwt.client.Utils.getToken;

public class EditTaskDialogWidget extends Composite {
    @UiField
    DialogBox dialog;

    @UiField
    TextBox name;

    @UiField
    ListBox author_id;

    @UiField
    ListBox executor_id;

    @UiField
    TextBox description;

    private ViewTaskForm viewTaskForm;
    private TasksClient client;
    private UserClient userClient;
    private TaskDto taskDto;
    private Long taskId;

    @UiTemplate("EditTaskDialog.ui.xml")
    interface AddTaskDialogBinder extends UiBinder<Widget, EditTaskDialogWidget> {
    }

    private static EditTaskDialogWidget.AddTaskDialogBinder uiBinder = GWT.create(EditTaskDialogWidget.AddTaskDialogBinder.class);

    public EditTaskDialogWidget(ViewTaskForm viewTaskForm, Long taskId) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.viewTaskForm = viewTaskForm;
        this.taskId = taskId;

        client = GWT.create(TasksClient.class);
        userClient = GWT.create(UserClient.class);
        GWT.log("taskId="+taskId.toString());
        client.getTask(getToken(), taskId.toString(), new MethodCallback<TaskDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Невозможно получить параметры задачи: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, TaskDto i) {
                taskDto = i;
                getUsers();

                dialog.center();
                dialog.show();
            }
        });
    }

    public void getUsers() {
        userClient.getUsers(getToken(), new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Невозможно получить список пользователей: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<UserDto> i) {
                GWT.log("Received " + i.size() + " users");
                for (UserDto o: i) {
                    executor_id.addItem(o.getUserName(), o.getUserId().toString());
                    author_id.addItem(o.getUserName(), o.getUserId().toString());

                    if (o.getUserId().equals(taskDto.getAuthorDto().getUserId())){
                        author_id.setItemSelected(author_id.getItemCount()-1, true);
                    }
                    if (o.getUserId().equals(taskDto.getExecutorDto().getUserId())){
                        executor_id.setItemSelected(executor_id.getItemCount()-1, true);
                    }
                }

                name.setText(taskDto.getName());
                description.setText(taskDto.getDescription());
            }
        });
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        TaskAddDto taskDto = new TaskAddDto(taskId, name.getText(), Long.parseLong(author_id.getSelectedValue()), Long.parseLong(executor_id.getSelectedValue()), description.getText());
        client.updateTask(getToken(), taskDto, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Ошибка при изменении задачи");
            }

            @Override
            public void onSuccess(Method method, Void aVoid) {
                dialog.hide();
                viewTaskForm.setTaskId(taskId);
                viewTaskForm.refresh();
            }
        });
    }

    @UiHandler("btnCancel")
    public void submitCancel(ClickEvent event) {
        dialog.hide();
    }

    @UiHandler("btnRemove")
    public void submitRemove(ClickEvent event) {
        client.removeTask(getToken(), taskId.toString(), new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, Void result) {
                viewTaskForm.setTaskId(taskId);
                viewTaskForm.refresh();
                dialog.hide();
            }
        });
    }
}
