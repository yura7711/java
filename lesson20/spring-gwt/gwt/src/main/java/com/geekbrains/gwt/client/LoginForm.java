package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.JwtAuthRequestDto;
import com.geekbrains.gwt.common.JwtAuthResponseDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class LoginForm extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox textUserlogin;

    @UiField
    TextBox textPassword;

    @UiTemplate("LoginForm.ui.xml")
    interface LoginFormBinder extends UiBinder<Widget, LoginForm> {
    }

    private TaskTableWidget taskTableWidget;
    private FilterTaskFormWidget filterTaskFormWidget;
    private TabLayoutPanel tabPanel;

    private static LoginFormBinder uiBinder = GWT.create(LoginFormBinder.class);

    public LoginForm(TabLayoutPanel tabPanel, TaskTableWidget taskTableWidget, FilterTaskFormWidget filterTaskFormWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.form.setAction(Defaults.getServiceRoot().concat("items"));
        this.taskTableWidget = taskTableWidget;
        this.tabPanel = tabPanel;
        this.filterTaskFormWidget = filterTaskFormWidget;
    }

    @UiHandler("form")
    public void onSubmit(FormPanel.SubmitEvent event) {
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        Window.alert(event.getResults());
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        JwtAuthRequestDto jwtAuthRequestDto = new JwtAuthRequestDto(textUserlogin.getValue(), textPassword.getValue());
        AuthClient authClient = GWT.create(AuthClient.class);
        authClient.authenticate(jwtAuthRequestDto, new MethodCallback<JwtAuthResponseDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, JwtAuthResponseDto jwtAuthResponseDto) {
                GWT.log(jwtAuthResponseDto.getToken());
                Utils.saveToken(jwtAuthResponseDto.getToken());
                taskTableWidget.refresh();
                filterTaskFormWidget.refresh();
                tabPanel.selectTab(1);
            }
        });
    }
}