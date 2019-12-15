// .ui.xml template last modified: 1576409297147
package com.geekbrains.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class TaskTableWidget_TaskTableBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.geekbrains.gwt.client.TaskTableWidget>, com.geekbrains.gwt.client.TaskTableWidget.TaskTableBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("Создать новую задачу")
    SafeHtml html1();
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.geekbrains.gwt.client.TaskTableWidget owner) {


    return new Widgets(owner).get_f_DecoratorPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final com.geekbrains.gwt.client.TaskTableWidget owner;


    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.submitClick((com.google.gwt.event.dom.client.ClickEvent) event);
      }
    };

    public Widgets(final com.geekbrains.gwt.client.TaskTableWidget owner) {
      this.owner = owner;
    }

    SafeHtml template_html1() {
      return template.html1();
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 0 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private com.geekbrains.gwt.client.TaskTableWidget_TaskTableBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private com.geekbrains.gwt.client.TaskTableWidget_TaskTableBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final com.geekbrains.gwt.client.TaskTableWidget_TaskTableBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.geekbrains.gwt.client.TaskTableWidget_TaskTableBinderImpl_GenBundle) GWT.create(com.geekbrains.gwt.client.TaskTableWidget_TaskTableBinderImpl_GenBundle.class);
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for f_DecoratorPanel1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.DecoratorPanel get_f_DecoratorPanel1() {
      return build_f_DecoratorPanel1();
    }
    private com.google.gwt.user.client.ui.DecoratorPanel build_f_DecoratorPanel1() {
      // Creation section.
      final com.google.gwt.user.client.ui.DecoratorPanel f_DecoratorPanel1 = (com.google.gwt.user.client.ui.DecoratorPanel) GWT.create(com.google.gwt.user.client.ui.DecoratorPanel.class);
      // Setup section.
      f_DecoratorPanel1.add(get_f_VerticalPanel2());

      return f_DecoratorPanel1;
    }

    /**
     * Getter for f_VerticalPanel2 called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_f_VerticalPanel2() {
      return build_f_VerticalPanel2();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_f_VerticalPanel2() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel f_VerticalPanel2 = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      f_VerticalPanel2.add(get_table());
      f_VerticalPanel2.add(get_createTask());
      f_VerticalPanel2.setSpacing(10);

      return f_VerticalPanel2;
    }

    /**
     * Getter for table called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.cellview.client.CellTable get_table() {
      return build_table();
    }
    private com.google.gwt.user.cellview.client.CellTable build_table() {
      // Creation section.
      final com.google.gwt.user.cellview.client.CellTable table = (com.google.gwt.user.cellview.client.CellTable) GWT.create(com.google.gwt.user.cellview.client.CellTable.class);
      // Setup section.
      table.setWidth("100%");
      table.setPageSize(15);

      this.owner.table = table;

      return table;
    }

    /**
     * Getter for createTask called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_createTask() {
      return build_createTask();
    }
    private com.google.gwt.user.client.ui.Button build_createTask() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button createTask = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      createTask.setHTML(template_html1().asString());
      createTask.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1);

      return createTask;
    }
  }
}
