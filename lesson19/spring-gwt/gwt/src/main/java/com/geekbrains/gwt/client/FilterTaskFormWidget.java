package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.StatusDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.gwt.common.UserDto;
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
import java.util.List;

public class FilterTaskFormWidget extends Composite {
    @UiField
    FormPanel filter;

    @UiField
    ListBox statusId = null;

    @UiField
    ListBox executer_id = null;

    @UiField
    ListBox author_id = null;

    private TaskTableWidget taskTableWidget;
    private TasksClient client;

    @UiTemplate("FilterTaskForm.ui.xml")
    interface FilterTaskFormBinder extends UiBinder<Widget, FilterTaskFormWidget> {
    }

    private static FilterTaskFormWidget.FilterTaskFormBinder uiBinder = GWT.create(FilterTaskFormWidget.FilterTaskFormBinder.class);

    public FilterTaskFormWidget(TaskTableWidget taskTableWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.taskTableWidget = taskTableWidget;
        client = GWT.create(TasksClient.class);

        client.getStatuses(new MethodCallback<List<StatusDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список Статусов: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<StatusDto> i) {
                GWT.log("Received " + i.size() + " statuses");
                for (StatusDto o: i) {
                    statusId.addItem(o.getRusTitle(), o.getStatusId().toString());
                }
            }
        });

        client.getUsers(new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список Статусов: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<UserDto> i) {
                GWT.log("Received " + i.size() + " users");
                for (UserDto o: i) {
                    executer_id.addItem(o.getUserName(), o.getUserId().toString());
                    author_id.addItem(o.getUserName(), o.getUserId().toString());
                }
            }
        });
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        Integer status = null;
        Long executer = null;
        Long author = null;
        try{
            status = Integer.parseInt(statusId.getSelectedValue());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            executer  = Long.parseLong(executer_id.getSelectedValue());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            author = Long.parseLong(author_id.getSelectedValue());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        client.getAllTasks(status
                ,executer
                ,author
                , new MethodCallback<List<TaskDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        GWT.log(throwable.toString());
                        GWT.log(throwable.getMessage());
                        Window.alert("Невозможно получить список задач: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, List<TaskDto> i) {
                        GWT.log("Received " + i.size() + " tasks");
                        taskTableWidget.loadTable(i);
                    }
                });
    }
}
