package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AddTaskDialogWidget extends Composite {
    @UiField
    DialogBox dialog;

    @UiField
    FormPanel form;

    @UiField
    TextBox name;

    @UiField
    ListBox author_id;

    @UiField
    ListBox executor_id;

    @UiField
    TextBox description;

    private TaskTableWidget taskTableWidget;
    private TasksClient client;

    @UiTemplate("AddTaskDialog.ui.xml")
    interface AddTaskDialogBinder extends UiBinder<Widget, AddTaskDialogWidget> {
    }

    private static AddTaskDialogWidget.AddTaskDialogBinder uiBinder = GWT.create(AddTaskDialogWidget.AddTaskDialogBinder.class);

    public AddTaskDialogWidget(TaskTableWidget taskTableWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.form.setAction(Defaults.getServiceRoot().concat("tasks/add"));
        this.taskTableWidget = taskTableWidget;

        dialog.center();
        dialog.show();

        client = GWT.create(TasksClient.class);

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
                    executor_id.addItem(o.getUserName(), o.getUserId().toString());
                    author_id.addItem(o.getUserName(), o.getUserId().toString());
                }
            }
        });
    }

    @UiHandler("form")
    public void onSubmit(FormPanel.SubmitEvent event) {
        if (name.getText().length() < 4) {
            Window.alert("Название задачи должно быть не менее 4 символов");
            event.cancel();
        }
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        dialog.hide();
        taskTableWidget.refresh();
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        form.submit();
    }

    @UiHandler("btnCancel")
    public void submitCancel(ClickEvent event) {
        dialog.hide();
    }
}
