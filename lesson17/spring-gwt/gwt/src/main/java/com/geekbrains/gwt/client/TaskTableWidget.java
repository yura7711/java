package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.TaskDto;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import java.util.List;

public class TaskTableWidget extends Composite {
    @UiField
    CellTable<TaskDto> table;

    private TasksClient client;

    @UiTemplate("TaskTable.ui.xml")
    interface TaskTableBinder extends UiBinder<Widget, TaskTableWidget> {
    }

    private static TaskTableBinder uiBinder = GWT.create(TaskTableBinder.class);

    public TaskTableWidget() {
        initWidget(uiBinder.createAndBindUi(this));
//         table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

        TextColumn<TaskDto> idColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getId().toString();
            }
        };
        table.addColumn(idColumn, "id");

        TextColumn<TaskDto> nameColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getName();
            }
        };
        table.addColumn(nameColumn, "name");

        TextColumn<TaskDto> authorColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getAuthor();
            }
        };
        table.addColumn(authorColumn, "author");

        TextColumn<TaskDto> executerColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getExecutor();
            }
        };
        table.addColumn(executerColumn, "executer");

        TextColumn<TaskDto> descriptionColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getDescription();
            }
        };
        table.addColumn(descriptionColumn, "description");

        client = GWT.create(TasksClient.class);

        Column<TaskDto, TaskDto> actionColumn = new Column<TaskDto, TaskDto>(
                new ActionCell<TaskDto>("REMOVE", new ActionCell.Delegate<TaskDto>() {
                    @Override
                    public void execute(TaskDto task) {
                        client.removeTask(task.getId().toString(), new MethodCallback<Void>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {
                                GWT.log(throwable.toString());
                                GWT.log(throwable.getMessage());
                            }

                            @Override
                            public void onSuccess(Method method, Void result) {
                                refresh();
                            }
                        });
                    }
                })) {
            @Override
            public TaskDto getValue(TaskDto taskDto) {
                return taskDto;
            }
        };

        table.addColumn(actionColumn, "Actions");

        table.setColumnWidth(idColumn, 100, Style.Unit.PX);
        table.setColumnWidth(nameColumn, 400, Style.Unit.PX);
        table.setColumnWidth(actionColumn, 200, Style.Unit.PX);

        refresh();
    }

    public void refresh() {
        client.getAllTasks(null, null, null, new MethodCallback<List<TaskDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список tasks: Сервер не отвечает" + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<TaskDto> i) {
                GWT.log("Received " + i.size() + " tasks");
                table.setRowData(i);
            }
        });
    }

    public void loadTable(List<TaskDto> i){
        table.setRowData(i);
    }

    @UiHandler("createTask")
    public void submitClick(ClickEvent event) {
        AddTaskDialogWidget addTaskDialogWidget = new AddTaskDialogWidget(this);
    }
}
