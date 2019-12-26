package com.geekbrains.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.*;

public class WebApp implements EntryPoint {
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8189/gwt-rest");
        TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Style.Unit.EM);
        ViewTaskForm viewTaskForm = new ViewTaskForm(tabPanel, null);
        TaskTableWidget taskTableWidget = new TaskTableWidget(tabPanel, viewTaskForm);
        FilterTaskFormWidget filterTaskFormWidget = new FilterTaskFormWidget(taskTableWidget);

        VerticalPanel verticalPanel = new VerticalPanel();
        Button logout = new Button("Выход", new ClickHandler() {
            public void onClick(ClickEvent event) {
                tabPanel.selectTab(0);
            }
        });
        verticalPanel.add(logout);
        verticalPanel.add(filterTaskFormWidget);
        verticalPanel.add(taskTableWidget);


        tabPanel.setAnimationDuration(100);
        tabPanel.getElement().getStyle().setMarginBottom(10.0, Style.Unit.PX);

        LoginForm loginForm = new LoginForm(tabPanel, taskTableWidget, filterTaskFormWidget);
        tabPanel.add(loginForm, "Login");

        tabPanel.add(verticalPanel, "Main Page");
        tabPanel.setHeight("800px");
        tabPanel.add(viewTaskForm,"View task");

        tabPanel.ensureDebugId("cwTabPanel");

        tabPanel.addSelectionHandler(event -> History.newItem("page" + event.getSelectedItem()));

        History.addValueChangeHandler(event -> {
            String historyToken = event.getValue();
            try {
                if (historyToken.substring(0, 4).equals("page")) {
                    String tabIndexToken = historyToken.substring(4, 5);
                    int tabIndex = Integer.parseInt(tabIndexToken);
                    tabPanel.selectTab(tabIndex);
                } else {
                    tabPanel.selectTab(0);
                }
            } catch (IndexOutOfBoundsException e) {
                tabPanel.selectTab(0);
            }
        });

        tabPanel.selectTab(0);

        Label header = new Label("GWT Demo Application");
        header.setStyleName("headerLabel");

        RootPanel.get().add(header);
        RootPanel.get().add(tabPanel);
    }
}