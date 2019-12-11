// .ui.xml template last modified: 1575895329744
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

public class AddItemFormWidget_AddItemFormBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.geekbrains.gwt.client.AddItemFormWidget>, com.geekbrains.gwt.client.AddItemFormWidget.AddItemFormBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("Создать")
    SafeHtml html1();
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.geekbrains.gwt.client.AddItemFormWidget owner) {


    return new Widgets(owner).get_f_DecoratorPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final com.geekbrains.gwt.client.AddItemFormWidget owner;


    final com.google.gwt.user.client.ui.FormPanel.SubmitHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1 = new com.google.gwt.user.client.ui.FormPanel.SubmitHandler() {
      public void onSubmit(com.google.gwt.user.client.ui.FormPanel.SubmitEvent event) {
        owner.onSubmit((com.google.gwt.user.client.ui.FormPanel.SubmitEvent) event);
      }
    };

    final com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames2 = new com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler() {
      public void onSubmitComplete(com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent event) {
        owner.onSubmitComplete((com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent) event);
      }
    };

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames3 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.submitClick((com.google.gwt.event.dom.client.ClickEvent) event);
      }
    };

    public Widgets(final com.geekbrains.gwt.client.AddItemFormWidget owner) {
      this.owner = owner;
    }

    SafeHtml template_html1() {
      return template.html1();
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 0 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private com.geekbrains.gwt.client.AddItemFormWidget_AddItemFormBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private com.geekbrains.gwt.client.AddItemFormWidget_AddItemFormBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final com.geekbrains.gwt.client.AddItemFormWidget_AddItemFormBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.geekbrains.gwt.client.AddItemFormWidget_AddItemFormBinderImpl_GenBundle) GWT.create(com.geekbrains.gwt.client.AddItemFormWidget_AddItemFormBinderImpl_GenBundle.class);
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
      f_DecoratorPanel1.add(get_form());

      return f_DecoratorPanel1;
    }

    /**
     * Getter for form called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.FormPanel get_form() {
      return build_form();
    }
    private com.google.gwt.user.client.ui.FormPanel build_form() {
      // Creation section.
      final com.google.gwt.user.client.ui.FormPanel form = (com.google.gwt.user.client.ui.FormPanel) GWT.create(com.google.gwt.user.client.ui.FormPanel.class);
      // Setup section.
      form.add(get_f_VerticalPanel2());
      form.setMethod("POST");
      form.addSubmitHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1);
      form.addSubmitCompleteHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames2);

      this.owner.form = form;

      return form;
    }

    /**
     * Getter for f_VerticalPanel2 called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_f_VerticalPanel2() {
      return build_f_VerticalPanel2();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_f_VerticalPanel2() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel f_VerticalPanel2 = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      f_VerticalPanel2.add(get_f_HorizontalPanel3());
      f_VerticalPanel2.add(get_f_HorizontalPanel5());
      f_VerticalPanel2.add(get_f_ListBox7());
      f_VerticalPanel2.add(get_btnSubmit());
      f_VerticalPanel2.setSpacing(10);

      return f_VerticalPanel2;
    }

    /**
     * Getter for f_HorizontalPanel3 called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.HorizontalPanel get_f_HorizontalPanel3() {
      return build_f_HorizontalPanel3();
    }
    private com.google.gwt.user.client.ui.HorizontalPanel build_f_HorizontalPanel3() {
      // Creation section.
      final com.google.gwt.user.client.ui.HorizontalPanel f_HorizontalPanel3 = (com.google.gwt.user.client.ui.HorizontalPanel) GWT.create(com.google.gwt.user.client.ui.HorizontalPanel.class);
      // Setup section.
      f_HorizontalPanel3.add(get_f_Label4());
      f_HorizontalPanel3.add(get_idText());

      return f_HorizontalPanel3;
    }

    /**
     * Getter for f_Label4 called 1 times. Type: DEFAULT. Build precedence: 5.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label4() {
      return build_f_Label4();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label4() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label4 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label4.setWidth("100px");
      f_Label4.setText("ID:");

      return f_Label4;
    }

    /**
     * Getter for idText called 1 times. Type: DEFAULT. Build precedence: 5.
     */
    private com.google.gwt.user.client.ui.TextBox get_idText() {
      return build_idText();
    }
    private com.google.gwt.user.client.ui.TextBox build_idText() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox idText = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.
      idText.setName("id");

      this.owner.idText = idText;

      return idText;
    }

    /**
     * Getter for f_HorizontalPanel5 called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.HorizontalPanel get_f_HorizontalPanel5() {
      return build_f_HorizontalPanel5();
    }
    private com.google.gwt.user.client.ui.HorizontalPanel build_f_HorizontalPanel5() {
      // Creation section.
      final com.google.gwt.user.client.ui.HorizontalPanel f_HorizontalPanel5 = (com.google.gwt.user.client.ui.HorizontalPanel) GWT.create(com.google.gwt.user.client.ui.HorizontalPanel.class);
      // Setup section.
      f_HorizontalPanel5.add(get_f_Label6());
      f_HorizontalPanel5.add(get_titleText());

      return f_HorizontalPanel5;
    }

    /**
     * Getter for f_Label6 called 1 times. Type: DEFAULT. Build precedence: 5.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label6() {
      return build_f_Label6();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label6() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label6 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label6.setWidth("100px");
      f_Label6.setText("Title:");

      return f_Label6;
    }

    /**
     * Getter for titleText called 1 times. Type: DEFAULT. Build precedence: 5.
     */
    private com.google.gwt.user.client.ui.TextBox get_titleText() {
      return build_titleText();
    }
    private com.google.gwt.user.client.ui.TextBox build_titleText() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox titleText = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.
      titleText.setName("title");

      this.owner.titleText = titleText;

      return titleText;
    }

    /**
     * Getter for f_ListBox7 called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.ListBox get_f_ListBox7() {
      return build_f_ListBox7();
    }
    private com.google.gwt.user.client.ui.ListBox build_f_ListBox7() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox f_ListBox7 = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.
      f_ListBox7.addItem("FOOD", "1");
      f_ListBox7.addItem("DEVICE", "2");
      f_ListBox7.addItem("ANOTHER", "3");
      f_ListBox7.setName("category");

      return f_ListBox7;
    }

    /**
     * Getter for btnSubmit called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.Button get_btnSubmit() {
      return build_btnSubmit();
    }
    private com.google.gwt.user.client.ui.Button build_btnSubmit() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button btnSubmit = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      btnSubmit.setHTML(template_html1().asString());
      btnSubmit.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames3);

      return btnSubmit;
    }
  }
}
