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
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import java.util.List;

import static com.geekbrains.gwt.client.Utils.getToken;

public class TaskTableWidget extends Composite {
    @UiField
    CellTable<TaskDto> table;

    private TasksClient client;

    @UiTemplate("TaskTable.ui.xml")
    interface TaskTableBinder extends UiBinder<Widget, TaskTableWidget> {
    }

    private static TaskTableBinder uiBinder = GWT.create(TaskTableBinder.class);

    public TaskTableWidget(TabLayoutPanel tabPanel, ViewTaskForm viewTaskForm) {
        TaskTableWidget taskTableWidget = this;
        initWidget(uiBinder.createAndBindUi(taskTableWidget));
        viewTaskForm.setTaskTableWidget(taskTableWidget);

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

        TextColumn<TaskDto> executerColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getExecutorDto().getUserName();
            }
        };
        table.addColumn(executerColumn, "executer");

        TextColumn<TaskDto> authorColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getAuthorDto().getUserName();
            }
        };
        table.addColumn(authorColumn, "author");

        TextColumn<TaskDto> descriptionColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getDescription();
            }
        };
        table.addColumn(descriptionColumn, "description");

        TextColumn<TaskDto> status = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto taskDto) {
                return taskDto.getStatusDto().getRusTitle();
            }
        };
        table.addColumn(status, "status");

        client = GWT.create(TasksClient.class);

        Column<TaskDto, TaskDto> viewTask = new Column<TaskDto, TaskDto>(
                new ActionCell<TaskDto>("Открыть задачу", new ActionCell.Delegate<TaskDto>() {
                    @Override
                    public void execute(TaskDto task) {
                        viewTaskForm.setTaskId(task.getId());
                        viewTaskForm.refresh();
                        tabPanel.selectTab(2);
                    }
                })) {
            @Override
            public TaskDto getValue(TaskDto taskDto) {
                return taskDto;
            }
        };

        table.addColumn(viewTask, "Просмотр");

        table.setColumnWidth(idColumn, 100, Style.Unit.PX);
        table.setColumnWidth(nameColumn, 400, Style.Unit.PX);
        table.setColumnWidth(viewTask, 200, Style.Unit.PX);
    }

    public void refresh() {
        client.getAllTasks(getToken(), null, null, null, new MethodCallback<List<TaskDto>>() {
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
