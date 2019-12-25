package com.geekbrains.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.*;

public class WebApp implements EntryPoint {
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8189/gwt-rest");

        TaskTableWidget taskTableWidget = new TaskTableWidget();
        TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Style.Unit.EM);
        VerticalPanel verticalPanel = new VerticalPanel();
        Button logout = new Button("Выход", new ClickHandler() {
            public void onClick(ClickEvent event) {
                tabPanel.selectTab(0);
            }
        });
        verticalPanel.add(logout);
        verticalPanel.add(new FilterTaskFormWidget(taskTableWidget));
        verticalPanel.add(taskTableWidget);


        tabPanel.setAnimationDuration(100);
        tabPanel.getElement().getStyle().setMarginBottom(10.0, Style.Unit.PX);

        LoginForm loginForm = new LoginForm(tabPanel, taskTableWidget);
        tabPanel.add(loginForm, "Login");

        tabPanel.add(verticalPanel, "Main Page");
        tabPanel.setHeight("800px");

        tabPanel.selectTab(0);
        tabPanel.ensureDebugId("cwTabPanel");
        tabPanel.getTabWidget(0).setVisible(false);
        tabPanel.getTabWidget(1).setVisible(false);

        RootPanel.get().add(tabPanel);
    }
}