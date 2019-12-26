package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import static com.geekbrains.gwt.client.Utils.getToken;

public class ViewTaskForm extends Composite {
    @UiField
    Label name;

    @UiField
    Label statusName;

    @UiField
    Label executor_name;

    @UiField
    Label author_name;

    @UiField
    Label description;

    @UiTemplate("ViewTaskForm.ui.xml")
    interface ViewTaskFormBinder extends UiBinder<Widget, ViewTaskForm> {
    }

    private TaskTableWidget taskTableWidget;
    private TasksClient client;
    private Long taskId;
    private TabLayoutPanel tabLayoutPanel;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public TaskTableWidget getTaskTableWidget() {
        return taskTableWidget;
    }

    public void setTaskTableWidget(TaskTableWidget taskTableWidget) {
        this.taskTableWidget = taskTableWidget;
    }

    private static ViewTaskFormBinder uiBinder = GWT.create(ViewTaskFormBinder.class);

    public ViewTaskForm(TabLayoutPanel tabPanel, Long taskId) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.taskId = taskId;
        this.tabLayoutPanel = tabPanel;
        client = GWT.create(TasksClient.class);
    }

    @UiHandler("btnCancel")
    public void submitCancel(ClickEvent event) {
        clear();
        taskTableWidget.refresh();
        tabLayoutPanel.selectTab(1);
    }

    @UiHandler("btnEditTask")
    public void submitEditTask(ClickEvent event) {
        if (taskId == null){
            Window.alert("Не указан идентификатор задачи");
        }

        EditTaskDialogWidget editTaskDialogWidget = new EditTaskDialogWidget(this, taskId);
    }

    @UiHandler("btnRemove")
    public void submitRemoveTask(ClickEvent event) {
        if (taskId == null){
            Window.alert("Не указан идентификатор задачи");
        }

        client.removeTask(getToken(), taskId.toString(), new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, Void result) {
                clear();
                taskTableWidget.refresh();
                tabLayoutPanel.selectTab(1);
            }
        });

    }

    public void refresh() {
        client.getTask(getToken(), taskId.toString(), new MethodCallback<TaskDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Невозможно получить параметры задачи: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, TaskDto i) {
                name.setText(i.getName());
                executor_name.setText(i.getExecutorDto().getUserName());
                author_name.setText(i.getAuthorDto().getUserName());
                description.setText(i.getDescription());
                statusName.setText(i.getStatusDto().getRusTitle());
            }
        });
    }

    public void clear() {
        taskId = null;
        name.setText(null);
        statusName.setText(null);
        executor_name.setText(null);
        author_name.setText(null);
        description.setText(null);
    }
}