package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.TaskAddDto;
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

public class AddTaskDialogWidget extends Composite {
    @UiField
    DialogBox dialog;

    @UiField
    TextBox name;

    @UiField
    ListBox executor_id;

    @UiField
    TextBox description;

    private TaskTableWidget taskTableWidget;
    private TasksClient client;
    private UserClient userClient;

    @UiTemplate("AddTaskDialog.ui.xml")
    interface AddTaskDialogBinder extends UiBinder<Widget, AddTaskDialogWidget> {
    }

    private static AddTaskDialogWidget.AddTaskDialogBinder uiBinder = GWT.create(AddTaskDialogWidget.AddTaskDialogBinder.class);

    public AddTaskDialogWidget(TaskTableWidget taskTableWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.taskTableWidget = taskTableWidget;

        dialog.center();
        dialog.show();

        client = GWT.create(TasksClient.class);
        userClient = GWT.create(UserClient.class);

        userClient.getUsers(getToken(), new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список Статусов: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<UserDto> i) {
                for (UserDto o: i) {
                    executor_id.addItem(o.getUserName(), o.getUserId().toString());
                }
            }
        });
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        TaskAddDto taskDto = new TaskAddDto(null, name.getText(), null, Long.parseLong(executor_id.getSelectedValue()), description.getText());
        client.createTask(getToken(), taskDto, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Ошибка при добавлении задачи: " + method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, Void aVoid) {
                dialog.hide();
                taskTableWidget.refresh();
            }
        });
    }

    @UiHandler("btnCancel")
    public void submitCancel(ClickEvent event) {
        dialog.hide();
    }
}
