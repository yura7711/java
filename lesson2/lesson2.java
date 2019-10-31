package com.geekbrains.training.lesson2.TaskSupport;

public class Task {
    long id;
    String name;
    String author;
    String executor;
    String description;
    private String status;

    public Task(long id, String name, String author, String executor, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.description = description;
        this.status = "Открыта";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


package com.geekbrains.training.lesson2.TaskSupport;

public class TaskTracker {
    private Task[] taskArray;

    public TaskTracker() {
        this.taskArray = new Task[10];
    }

    private boolean isTaskExist(Task task) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null && taskArray[i].id == task.id)
                return true;
        }
        return false;
    }

    public void addTask(Task newTask) {
        if (isTaskExist(newTask)) {
            System.out.println("Задача с id=" + newTask.id + " уже существует. Добавление не выполнено!");
            return;
        }

        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                taskArray[i] = newTask;
                System.out.println("Задача с id=" + newTask.id + " добавлена в позицию " + (i + 1));
                return;
            }
        }
        System.out.println("Список задач заполнен");
    }

    public void printTaskArray() {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null) {
                System.out.println("--------------------------------");
                System.out.println("Идентификатор задачи: " + taskArray[i].id);
                System.out.println("Задача: " + taskArray[i].name);
                System.out.println("Статус: " + taskArray[i].getStatus());
                System.out.println("Исполнитель: " + taskArray[i].executor);
                System.out.println("Автор: " + taskArray[i].author);
                System.out.println("Описание: " + taskArray[i].description);
                System.out.println("--------------------------------");
            }
        }
    }

    public void updateTaskStatus(long idTask, String newStatus){
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null && taskArray[i].id == idTask) {

                taskArray[i].setStatus(newStatus);
                System.out.println("Задача с id=" + idTask + " успешно удалена");
            }
        }
    }

    public void deleteTask(long idTask) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null && taskArray[i].id == idTask) {
                taskArray[i] = null;
                System.out.println("Задача с id=" + idTask + " успешно удалена");
            }
        }
    }

    public void deleteTask(String nameTask) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != null && taskArray[i].name.equals(nameTask)) {
                taskArray[i] = null;
                System.out.println("Задача с именем \"" + nameTask +"\" успешно удалена");
            }
        }
    }
}


package com.geekbrains.training.lesson2;

import com.geekbrains.training.lesson2.TaskSupport.Task;
import com.geekbrains.training.lesson2.TaskSupport.TaskTracker;

public class MainApp {
    public static void main(String[] args) {
        TaskTracker myTaskTracker = new TaskTracker();

        myTaskTracker.addTask(new Task(1,"Выполнить обзвон Клиентов часть 1","Иван", "Иван", "Выполнить обзвон Клиентов по списку (первая часть)"));

        myTaskTracker.addTask(new Task(2,"Выполнить обзвон Клиентов часть 2","Иван", "Петя", "Выполнить обзвон Клиентов по списку (вторая часть)"));
        myTaskTracker.addTask(new Task(3,"Отправить письмо","Митя", "Митя", "Предложить КП по интернету Клиенту 1"));
        myTaskTracker.addTask(new Task(4,"Сходить на встречу с Клиентом","Митя", "Митя", "Обсудить КП по интернету"));
        myTaskTracker.addTask(new Task(5,"Выполнить обзвон Клиентов часть 3","Иван", "Митя", "Выполнить обзвон Клиентов по списку (третья часть)"));

        myTaskTracker.printTaskArray();

        myTaskTracker.deleteTask(2);

        myTaskTracker.printTaskArray();

        myTaskTracker.deleteTask("Отправить письмо");

        myTaskTracker.printTaskArray();

        myTaskTracker.addTask(new Task(1,"Покормить кота","Таня", "Таня", "Покормить Мурзика"));
        myTaskTracker.addTask(new Task(16,"Оформить командировку","Ольга", "Ольга", "Оформить командировку в Пермь"));
        myTaskTracker.updateTaskStatus(16,"В работе");
        myTaskTracker.addTask(new Task(17,"Создать домен","Ольга", "Иван", "Создать новый домен для Клиента 2"));
        myTaskTracker.addTask(new Task(18,"Заключить договор","Таня", "Иван", "Заключить договор с Клиентом 3"));
        myTaskTracker.addTask(new Task(19,"Расторгнуть договор","Ольга", "Иван", "Расторгнуть договор с Клиентом 4"));
        myTaskTracker.addTask(new Task(20,"Реализовать приостановление договоров","Ольга", "Иван", "Реализовать визард приостановления договора"));
        myTaskTracker.addTask(new Task(21,"Оформить командировку","Ольга", "Митя", "Оформить командировку в СПБ"));
        myTaskTracker.addTask(new Task(2,"Покормить кота","Ольга", "Ольга", "Покормить Мурзика"));
        myTaskTracker.addTask(new Task(3,"Отправить письмо","Митя", "Митя", "Предложить КП по интернету Клиенту 1"));

        myTaskTracker.printTaskArray();
    }
}
