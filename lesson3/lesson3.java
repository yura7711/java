package com.geekbrains.training.lesson3.entities;

public class Task {
    public enum Status {
        created("Создана"), inWork("В работе"), closed("Закрыта"), rejected("Отклонена");

        private String rusTitle;

        Status(String rusTitle) {
            this.rusTitle = rusTitle;
        }

        public String getRusTitle() {
            return rusTitle;
        }
    }

    private Long id;
    private String name;
    private String author;
    private String executor;
    private String description;
    private Status status;

    public Task(Long id, String name, String author, String executor, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.description = description;
        this.status = Status.created;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getExecutor() {
        return executor;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status.getRusTitle();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


package com.geekbrains.training.lesson3.taskSupport;

import com.geekbrains.training.lesson3.entities.Task;

public class TaskRepository implements TaskService{
    private Task[] taskArray;

    public TaskRepository() {
        this.taskArray = new Task[10];
    }

    private boolean isTaskExist(Task task) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null && taskArray[i].getId() == task.getId())
                return true;
        }
        return false;
    }

    @Override
    public void addTask(Task newTask) {
        if (isTaskExist(newTask)) {
            System.out.println("Задача с id=" + newTask.getId() + " уже существует. Добавление не выполнено!");
            return;
        }

        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                taskArray[i] = newTask;
                System.out.println("Задача с id=" + newTask.getId() + " добавлена в позицию " + (i + 1));
                return;
            }
        }
        System.out.println("Список задач заполнен");
    }

    @Override
    public void printTaskArray() {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null) {
                System.out.println("--------------------------------");
                System.out.println("Идентификатор задачи: " + taskArray[i].getId());
                System.out.println("Задача: " + taskArray[i].getName());
                System.out.println("Статус: " + taskArray[i].getStatus());
                System.out.println("Исполнитель: " + taskArray[i].getExecutor());
                System.out.println("Автор: " + taskArray[i].getAuthor());
                System.out.println("Описание: " + taskArray[i].getDescription());
                System.out.println("--------------------------------");
            }
        }
    }

    @Override
    public void updateTaskStatus(Long idTask, Task.Status newStatus){
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null && taskArray[i].getId() == idTask) {

                taskArray[i].setStatus(newStatus);
                System.out.println("Задача с id=" + idTask + " успешно изменена");
                return;
            }
        }
    }

    @Override
    public void deleteTask(Long idTask) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null && taskArray[i].getId() == idTask) {
                taskArray[i] = null;
                System.out.println("Задача с id=" + idTask + " успешно удалена");
                return;
            }
        }
    }

    @Override
    public void deleteTask(String nameTask) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null && taskArray[i].getName().equals(nameTask)) {
                taskArray[i] = null;
                System.out.println("Задача с именем \"" + nameTask +"\" успешно удалена");
            }
        }
    }
}



package com.geekbrains.training.lesson3.taskSupport;

import com.geekbrains.training.lesson3.entities.Task;

public interface TaskService {
    void addTask(Task newTask);
    void printTaskArray();
    void updateTaskStatus(Long idTask, Task.Status newStatus);
    void deleteTask(Long idTask);
    void deleteTask(String nameTask);
}



package com.geekbrains.training.lesson3;

import com.geekbrains.training.lesson3.entities.Task;
import com.geekbrains.training.lesson3.taskSupport.TaskService;
import com.geekbrains.training.lesson3.taskSupport.TaskRepository;

public class MainApp {
    public static void main(String[] args) {
        TaskService myTaskService = new TaskRepository();

        myTaskService.addTask(new Task(1L,"Выполнить обзвон Клиентов часть 1","Иван", "Иван", "Выполнить обзвон Клиентов по списку (первая часть)"));

        myTaskService.addTask(new Task(2L,"Выполнить обзвон Клиентов часть 2","Иван", "Петя", "Выполнить обзвон Клиентов по списку (вторая часть)"));
        myTaskService.addTask(new Task(3L,"Отправить письмо","Митя", "Митя", "Предложить КП по интернету Клиенту 1"));
        myTaskService.addTask(new Task(4L,"Сходить на встречу с Клиентом","Митя", "Митя", "Обсудить КП по интернету"));
        myTaskService.addTask(new Task(5L,"Выполнить обзвон Клиентов часть 3","Иван", "Митя", "Выполнить обзвон Клиентов по списку (третья часть)"));

        myTaskService.printTaskArray();

        myTaskService.deleteTask(2L);

        myTaskService.printTaskArray();

        myTaskService.deleteTask("Отправить письмо");

        myTaskService.printTaskArray();

        myTaskService.addTask(new Task(1L,"Покормить кота","Таня", "Таня", "Покормить Мурзика"));
        myTaskService.addTask(new Task(16L,"Оформить командировку","Ольга", "Ольга", "Оформить командировку в Пермь"));
        myTaskService.updateTaskStatus(16L,Task.Status.inWork);
        myTaskService.addTask(new Task(17L,"Создать домен","Ольга", "Иван", "Создать новый домен для Клиента 2"));
        myTaskService.addTask(new Task(18L,"Заключить договор","Таня", "Иван", "Заключить договор с Клиентом 3"));
        myTaskService.addTask(new Task(19L,"Расторгнуть договор","Ольга", "Иван", "Расторгнуть договор с Клиентом 4"));
        myTaskService.addTask(new Task(20L,"Реализовать приостановление договоров","Ольга", "Иван", "Реализовать визард приостановления договора"));
        myTaskService.addTask(new Task(21L,"Оформить командировку","Ольга", "Митя", "Оформить командировку в СПБ"));
        myTaskService.addTask(new Task(2L,"Покормить кота","Ольга", "Ольга", "Покормить Мурзика"));
        myTaskService.addTask(new Task(3L,"Отправить письмо","Митя", "Митя", "Предложить КП по интернету Клиенту 1"));

        myTaskService.printTaskArray();
    }
}
