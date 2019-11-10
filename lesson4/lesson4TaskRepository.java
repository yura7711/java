package com.geekbrains.training.lesson4.entities;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)){
            return false;
        }
        return this.id.equals(((Task)obj).getId());

    }
}

package com.geekbrains.training.lesson4.repositories;

public class RepositoryExceptions extends RuntimeException {
    public RepositoryExceptions(String message) {
        super(message);
    }
}


package com.geekbrains.training.lesson4.repositories;

public class ArrayIsFullException extends RepositoryExceptions {
    public ArrayIsFullException() {
        super("Список задач заполнен");
    }
}


package com.geekbrains.training.lesson4.repositories;

public class TaskIsExistsException extends RepositoryExceptions{
    private Long taskId;

    public TaskIsExistsException(Long taskId) {
        super("Задача с id=" + taskId + " уже существует. Добавление не выполнено!");
        this.taskId = taskId;
    }
}


package com.geekbrains.training.lesson4.repositories;

public class TaskNotDeletedException extends RepositoryExceptions{
    public TaskNotDeletedException(Long taskId) {
        super("Задача c id=" + taskId + " не была удалена");
    }

    public TaskNotDeletedException(String taskName) {
        super("Задача c именем \"" + taskName + "\" не была удалена");
    }
}


package com.geekbrains.training.lesson4.repositories;

public class TaskNotUpdatedException extends RepositoryExceptions {
    public TaskNotUpdatedException(Long taskId) {
        super("Задача c id=" + taskId + " не была обновлена");
    }
}



package com.geekbrains.training.lesson4.repositories;

import com.geekbrains.training.lesson4.entities.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayTaskRepository implements TaskRepository {
    private List<Task> taskList;

    public ArrayTaskRepository() {
        this.taskList = new ArrayList<>(10);
    }

    @Override
    public void addTask(Task newTask) {
        if (taskList.contains(newTask)){
            throw new TaskIsExistsException(newTask.getId());
        }

        if (taskList.size() > 10) {
            throw new ArrayIsFullException();
        }
        else{
            taskList.add(newTask);
        }
    }

    @Override
    public List<Task> getTaskArray() {
        return taskList;
    }

    @Override
    public void updateTaskStatus(Long idTask, Task.Status newStatus){
        for (Task o : taskList) {
            if (o.getId().equals(idTask)){
                o.setStatus(newStatus);
                return;
            }
        }
        throw new TaskNotUpdatedException(idTask);
    }

    @Override
    public void deleteTask(Long idTask) {
        Iterator<Task> iter = taskList.iterator();
        while(iter.hasNext()){
            Task tmpTask = iter.next();
            if (tmpTask.getId().equals(idTask)){
                iter.remove();
                return;
            }
        }
        throw new TaskNotDeletedException(idTask);
    }

    @Override
    public void deleteTask(String nameTask) {
        Iterator<Task> iter = taskList.iterator();
        while(iter.hasNext()){
            Task tmpTask = iter.next();
            if (tmpTask.getName().equals(nameTask)){
                iter.remove();
                return;
            }
        }
        throw new TaskNotDeletedException(nameTask);
    }
}


package com.geekbrains.training.lesson4.repositories;

import com.geekbrains.training.lesson4.entities.Task;

import java.util.List;

public interface TaskRepository {
    void addTask(Task newTask);
    List<Task> getTaskArray();
    void updateTaskStatus(Long idTask, Task.Status newStatus);
    void deleteTask(Long idTask);
    void deleteTask(String nameTask);
}


package com.geekbrains.training.lesson4.services;

import com.geekbrains.training.lesson4.repositories.ArrayTaskRepository;
import com.geekbrains.training.lesson4.repositories.RepositoryExceptions;
import com.geekbrains.training.lesson4.repositories.TaskRepository;
import com.geekbrains.training.lesson4.entities.Task;

public class TaskService {
    private TaskRepository taskRepository = new ArrayTaskRepository();

    public void printTaskArray() {
        for (Task o : taskRepository.getTaskArray()) {
            if (o != null) {
                System.out.println("--------------------------------");
                System.out.println("Идентификатор задачи: " + o.getId());
                System.out.println("Задача: " + o.getName());
                System.out.println("Статус: " + o.getStatus());
                System.out.println("Исполнитель: " + o.getExecutor());
                System.out.println("Автор: " + o.getAuthor());
                System.out.println("Описание: " + o.getDescription());
                System.out.println("--------------------------------");
            }
        }
    }

    public void addTask(Task newTask) {
        try {
            taskRepository.addTask(newTask);
            System.out.println("Задача с id=" + newTask.getId() + " добавлена в массив");
        }
        catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTaskStatus(Long idTask, Task.Status newStatus){
        try {
            taskRepository.updateTaskStatus(idTask, newStatus);
            System.out.println("Задача с id=" + idTask + " успешно изменена");
        }
        catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(Long idTask) {
        try {
            taskRepository.deleteTask(idTask);
            System.out.println("Задача с id=" + idTask + " успешно удалена");
        }
        catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String nameTask) {
        try {
            taskRepository.deleteTask(nameTask);
            System.out.println("Задача с именем \"" + nameTask + "\" успешно удалена");
        }
        catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}


package com.geekbrains.training.lesson4;

import com.geekbrains.training.lesson4.entities.Task;
import com.geekbrains.training.lesson4.services.TaskService;

public class MainApp {
    public static void main(String[] args) {
        TaskService myTaskService = new TaskService();

        myTaskService.addTask(new Task(1L, "Выполнить обзвон Клиентов часть 1", "Иван", "Иван", "Выполнить обзвон Клиентов по списку (первая часть)"));

        myTaskService.addTask(new Task(1L, "Выполнить обзвон Клиентов часть 1", "Иван", "Иван", "Выполнить обзвон Клиентов по списку (первая часть)"));

        myTaskService.addTask(new Task(2L, "Выполнить обзвон Клиентов часть 2", "Иван", "Петя", "Выполнить обзвон Клиентов по списку (вторая часть)"));
        myTaskService.addTask(new Task(3L, "Отправить письмо", "Митя", "Митя", "Предложить КП по интернету Клиенту 1"));
        myTaskService.addTask(new Task(4L, "Сходить на встречу с Клиентом", "Митя", "Митя", "Обсудить КП по интернету"));
        myTaskService.addTask(new Task(5L, "Выполнить обзвон Клиентов часть 3", "Иван", "Митя", "Выполнить обзвон Клиентов по списку (третья часть)"));

        myTaskService.printTaskArray();

        myTaskService.deleteTask(2L);
        myTaskService.deleteTask(222L);

        myTaskService.printTaskArray();

        myTaskService.deleteTask("Отправить письмо");
        myTaskService.deleteTask("Этой задачи нет в репозитории");

        myTaskService.printTaskArray();

        myTaskService.addTask(new Task(1L, "Покормить кота", "Таня", "Таня", "Покормить Мурзика"));

        myTaskService.addTask(new Task(16L, "Оформить командировку", "Ольга", "Ольга", "Оформить командировку в Пермь"));
        myTaskService.updateTaskStatus(16L, Task.Status.inWork);
        myTaskService.updateTaskStatus(166L, Task.Status.inWork);

        myTaskService.addTask(new Task(17L, "Создать домен", "Ольга", "Иван", "Создать новый домен для Клиента 2"));
        myTaskService.addTask(new Task(18L, "Заключить договор", "Таня", "Иван", "Заключить договор с Клиентом 3"));
        myTaskService.addTask(new Task(19L, "Расторгнуть договор", "Ольга", "Иван", "Расторгнуть договор с Клиентом 4"));
        myTaskService.addTask(new Task(20L, "Реализовать приостановление договоров", "Ольга", "Иван", "Реализовать визард приостановления договора"));
        myTaskService.addTask(new Task(21L, "Оформить командировку", "Ольга", "Митя", "Оформить командировку в СПБ"));
        myTaskService.addTask(new Task(2L, "Покормить кота", "Ольга", "Ольга", "Покормить Мурзика"));
        myTaskService.addTask(new Task(3L, "Отправить письмо", "Митя", "Митя", "Предложить КП по интернету Клиенту 1"));

        myTaskService.printTaskArray();
    }
}
