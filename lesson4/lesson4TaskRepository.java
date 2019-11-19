package com.geekbrains.training.lesson10.entities;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = -277821547227621214L;

    public enum Status {
        created(1,"Создана", 1)
        , inWork(2,"В работе", 2)
        , closed(3,"Закрыта", 3)
        , rejected(4,"Отклонена", 4);



        private int statusId;
        private String rusTitle;
        private int priority;

        Status(int statusId, String rusTitle, int priority) {
            this.statusId = statusId;
            this.rusTitle = rusTitle;
            this.priority = priority;
        }

        public String getRusTitle() {
            return rusTitle;
        }

        public int getPriority() {
            return priority;
        }

        public int getStatusId() { return statusId; }

        public static Status getStatusByRusName(String rusName){
            for (Status status: Status.values()) {
                if (status.getRusTitle().equals(rusName)){
                    return status;
                }
            }
            return null;
        }

        public static Status getStatusById(int statusId){
            for (Status status: Status.values()) {
                if (status.getStatusId() == statusId){
                    return status;
                }
            }
            return null;
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

    public Task(Long id, String name, String author, String executor, String description, Status status) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.description = description;
        this.status = status;
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

    public Status getEnumStatus() {
        return status;
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

    @Override
    public String toString() {
        return "***\nid=" + id + " " + name + "\nСтатус: " + status.getRusTitle() + "\nИсполнитель: " + executor + "\nАвтор: " + author + "\nОписание: " + description;
    }
}

package com.geekbrains.training.lesson10.repositories;

public class RepositoryExceptions extends RuntimeException {
    public RepositoryExceptions(String message) {
        super(message);
    }
}

package com.geekbrains.training.lesson10.repositories;

public class TaskIsExistsException extends RepositoryExceptions {
    private Long taskId;

    public TaskIsExistsException(Long taskId) {
        super("Задача с id=" + taskId + " уже существует. Добавление не выполнено!");
        this.taskId = taskId;
    }
}


package com.geekbrains.training.lesson10.repositories;

public class TaskNotDeletedException extends RepositoryExceptions {
    public TaskNotDeletedException(Long taskId) {
        super("Задача c id=" + taskId + " не была удалена");
    }

    public TaskNotDeletedException(String taskName) {
        super("Задача c именем \"" + taskName + "\" не была удалена");
    }
}


package com.geekbrains.training.lesson10.repositories;

public class TaskNotUpdatedException extends RepositoryExceptions {
    public TaskNotUpdatedException(Long taskId) {
        super("Задача c id=" + taskId + " не была обновлена");
    }
}



package com.geekbrains.training.lesson10.repositories;

import com.geekbrains.training.lesson10.entities.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayTaskRepository implements TaskRepository {
    private List<Task> taskList;

    public ArrayTaskRepository() {
        this.taskList = new ArrayList<>(10);
    }

    @Override
    public void connect(){

    };

    @Override
    public void disconnect(){

    };

    @Override
    public void addTask(Task newTask) {
        if (taskList.contains(newTask)){
            throw new TaskIsExistsException(newTask.getId());
        }

        taskList.add(newTask);
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


package com.geekbrains.training.lesson10.repositories;

import com.geekbrains.training.lesson10.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleDBRepository implements TaskRepository {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement preparedStatement;
    private List<Task> taskList;

    public OracleDBRepository() {
        this.taskList = new ArrayList<>();
    }

    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/orcle","training", "Qwerty123");
            stmt = connection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        try {
            preparedStatement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        try {
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(Task newTask) {
        try {
            preparedStatement = connection.prepareStatement("select 1 from tasks where task_id = ?");
            preparedStatement.setLong(1, newTask.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                if (rs.getInt(1) > 0){
                    throw new TaskIsExistsException(newTask.getId());
                }
            }

            preparedStatement = connection.prepareStatement("insert into tasks(task_id, task_name, author, executor, description, status_id) values(?, ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, newTask.getId());
            preparedStatement.setString(2, newTask.getName());
            preparedStatement.setString(3, newTask.getAuthor());
            preparedStatement.setString(4, newTask.getExecutor());
            preparedStatement.setString(5, newTask.getDescription());
            preparedStatement.setInt(6, newTask.getEnumStatus().getStatusId());
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getTaskArray() {
        taskList.clear();

        try {
            ResultSet rs = stmt.executeQuery("select task_id, task_name, author, executor, description, status_id from tasks");
            while(rs.next()) {
                taskList.add(new Task(rs.getLong(1)
                        , rs.getString(2)
                        , rs.getString(3)
                        , rs.getString(4)
                        , rs.getString(5)
                        , Task.Status.getStatusById(rs.getInt(6)))
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return taskList;
    }

    @Override
    public void updateTaskStatus(Long idTask, Task.Status newStatus) {
        try {
            preparedStatement = connection.prepareStatement("update tasks set status_id = ? where task_id = ?");
            preparedStatement.setInt(1, newStatus.getStatusId());
            preparedStatement.setLong(2, idTask);

            if (preparedStatement.executeUpdate() == 0){
                throw new TaskNotUpdatedException(idTask);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(Long idTask) {
        try {
            preparedStatement = connection.prepareStatement("delete from tasks where task_id = ?");
            preparedStatement.setLong(1, idTask);

            if (preparedStatement.executeUpdate() == 0){
                throw new TaskNotDeletedException(idTask);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(String nameTask) {
        try {
            preparedStatement = connection.prepareStatement("delete from tasks where task_name = ?");
            preparedStatement.setString(1, nameTask);

            if (preparedStatement.executeUpdate() == 0){
                throw new TaskNotDeletedException(nameTask);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}


package com.geekbrains.training.lesson10.repositories;

import com.geekbrains.training.lesson10.entities.Task;

import java.util.List;

public interface TaskRepository {
    void connect();
    void disconnect();
    void addTask(Task newTask);
    List<Task> getTaskArray();
    void updateTaskStatus(Long idTask, Task.Status newStatus);
    void deleteTask(Long idTask);
    void deleteTask(String nameTask);
}


package com.geekbrains.training.lesson10.services;

import com.geekbrains.training.lesson10.repositories.ArrayTaskRepository;
import com.geekbrains.training.lesson10.repositories.OracleDBRepository;
import com.geekbrains.training.lesson10.repositories.RepositoryExceptions;
import com.geekbrains.training.lesson10.repositories.TaskRepository;
import com.geekbrains.training.lesson10.entities.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private TaskRepository taskRepository = new OracleDBRepository();
    String lineSeparator = System.getProperty("line.separator");
    String separator = "&&";

    public void printTaskArray() {
        for (Task o : taskRepository.getTaskArray()) {
            if (o != null) {
                System.out.println(o);
            }
        }
    }

    public void connect(){
        taskRepository.connect();
    };

    public void disconnect(){
        taskRepository.disconnect();
    };

    public void addTask(Task newTask) {
        try {
            taskRepository.addTask(newTask);
            System.out.println("Задача с id=" + newTask.getId() + " добавлена в массив");
        } catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTaskStatus(Long idTask, Task.Status newStatus) {
        try {
            taskRepository.updateTaskStatus(idTask, newStatus);
            System.out.println("Задача с id=" + idTask + " успешно изменена");
        } catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(Long idTask) {
        try {
            taskRepository.deleteTask(idTask);
            System.out.println("Задача с id=" + idTask + " успешно удалена");
        } catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String nameTask) {
        try {
            taskRepository.deleteTask(nameTask);
            System.out.println("Задача с именем \"" + nameTask + "\" успешно удалена");
        } catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    //Получение списка задач по выбранному статусу
    public List<Task> getTaskByStatus(Task.Status status) {
        return taskRepository.getTaskArray().stream().filter(task -> task.getEnumStatus() == status).collect(Collectors.toList());
    }

    //Проверка наличия задачи с указанным ID
    public boolean checkTaskById(Long id) {
        return taskRepository.getTaskArray().stream()
                .filter(task -> task.getId().equals(id)).count() > 0;
    }

    //Получение списка задач в отсортированном по статусу виде: открыта, в работе, закрыта
    // (можете выбирать любой статус и любой порядок, главное чтобы было 3 разных статуса);
    public List<Task> getSortTaskByStatus() {
        return taskRepository.getTaskArray().stream()
                .sorted((t1, t2) -> (t1.getEnumStatus().getPriority() - t2.getEnumStatus().getPriority()))
                .collect(Collectors.toList());
    }

    //Подсчет количества задач по определенному статусу
    public int getCountTaskByStatus(Task.Status status) {
        return taskRepository.getTaskArray().stream().filter(task -> task.getEnumStatus() == status).collect(Collectors.toList()).size();
    }

    public List<Task> getAllTask() {
        return taskRepository.getTaskArray();
    }

    //Заполнить список задач из файла
    public List<Task> importTaskFromFile() {
        List<Task> newList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Task.txt"))) {
            String line;
            String[] params = new String[5];
            while ((line = br.readLine()) != null) {
                params = line.split(separator);
                newList.add(new Task(Long.parseLong(params[0]), params[1], params[2], params[3], params[4], Task.Status.getStatusByRusName(params[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newList;
    }

    //Записать список задач в файл
    public void exportTaskInFile(List<Task> listTask) {
        try (PrintWriter pw = new PrintWriter("Task.txt")) {
            for (Task task : listTask) {
                pw.print(task.getId() + separator);
                pw.print(task.getName() + separator);
                pw.print(task.getAuthor() + separator);
                pw.print(task.getExecutor() + separator);
                pw.print(task.getDescription() + separator);
                pw.print(task.getEnumStatus().getRusTitle());
                pw.print(lineSeparator);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package com.geekbrains.training.lesson10;

import com.geekbrains.training.lesson10.entities.Task;
import com.geekbrains.training.lesson10.repositories.TaskRepository;
import com.geekbrains.training.lesson10.services.TaskService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        TaskService myTaskService = new TaskService();
        myTaskService.connect();
        myTaskService.addTask(new Task(1L, "Выполнить обзвон Клиентов часть 1", "Иван", "Иван", "Выполнить обзвон Клиентов по списку (первая часть)"));
        myTaskService.addTask(new Task(1L, "Выполнить", "Иван", "Иван", "Выполнить"));
        myTaskService.updateTaskStatus(1L, Task.Status.inWork);
        myTaskService.addTask(new Task(2L, "Выполнить обзвон Клиентов часть 1", "Иван", "Иван", "Выполнить обзвон Клиентов по списку (первая часть)"));
        myTaskService.addTask(new Task(3L, "Выполнить обзвон Клиентов часть 2", "Иван", "Петя", "Выполнить обзвон Клиентов по списку (вторая часть)"));
        myTaskService.addTask(new Task(4L, "Отправить письмо", "Митя", "Митя", "Предложить КП по интернету Клиенту 1"));
        myTaskService.addTask(new Task(5L, "Сходить на встречу с Клиентом", "Митя", "Митя", "Обсудить КП по интернету"));
        myTaskService.addTask(new Task(6L, "Выполнить обзвон Клиентов часть 3", "Иван", "Митя", "Выполнить обзвон Клиентов по списку (третья часть)"));
        myTaskService.addTask(new Task(7L, "Покормить кота", "Таня", "Таня", "Покормить Мурзика"));
        myTaskService.addTask(new Task(16L, "Оформить командировку", "Ольга", "Ольга", "Оформить командировку в Пермь"));
        myTaskService.updateTaskStatus(16L, Task.Status.inWork);
        myTaskService.addTask(new Task(17L, "Создать домен", "Ольга", "Иван", "Создать новый домен для Клиента 2"));
        myTaskService.addTask(new Task(18L, "Заключить договор", "Таня", "Иван", "Заключить договор с Клиентом 3"));
        myTaskService.addTask(new Task(19L, "Расторгнуть договор", "Ольга", "Иван", "Расторгнуть договор с Клиентом 4"));
        myTaskService.updateTaskStatus(19L, Task.Status.closed);
        myTaskService.addTask(new Task(20L, "Реализовать приостановление договоров", "Ольга", "Иван", "Реализовать визард приостановления договора"));
        myTaskService.updateTaskStatus(20L, Task.Status.rejected);
        myTaskService.addTask(new Task(21L, "Оформить командировку", "Ольга", "Митя", "Оформить командировку в СПБ"));
        myTaskService.addTask(new Task(22L, "Покормить кота", "Ольга", "Ольга", "Покормить Мурзика"));
        myTaskService.addTask(new Task(23L, "Отправить письмо", "Митя", "Митя", "Предложить КП по интернету Клиенту 1"));
        myTaskService.deleteTask(23L);
        myTaskService.deleteTask("Отправить письмо");

        System.out.println(">>>Получение списка задач по выбранному статусу");
        for (Task o : myTaskService.getTaskByStatus(Task.Status.inWork)) {
            System.out.println(o);
        }

        System.out.println(">>>Проверка наличия задачи с указанным ID");
        System.out.println(myTaskService.checkTaskById(3L));

        //Получение списка задач в отсортированном по статусу виде: открыта, в работе, закрыта
        // (можете выбирать любой статус и любой порядок, главное чтобы было 3 разных статуса);
        System.out.println(">>>Получение списка задач в отсортированном по статусу виде");
        for (Task o : myTaskService.getSortTaskByStatus()) {
            System.out.println(o);
        }

        System.out.println(">>>Подсчет количества задач по определенному статусу");
        System.out.println(myTaskService.getCountTaskByStatus(Task.Status.created));

        myTaskService.exportTaskInFile(myTaskService.getAllTask());

        List<Task> importTasks = myTaskService.importTaskFromFile();

        for (Task task: importTasks) {
            System.out.println(task);
        }

        myTaskService.disconnect();
    }
}

