package com.geekbrains.training.lesson11.services;

import com.geekbrains.training.lesson11.repositories.RepositoryExceptions;
import com.geekbrains.training.lesson11.repositories.TaskRepository;
import com.geekbrains.training.lesson11.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier(value = "oracleDBRepository")
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void printTaskArray() {
        for (Task o : taskRepository.getTaskArray()) {
            if (o != null) {
                System.out.println(o);
            }
        }
    }

    public void shutdown(){
        taskRepository.shutdown();
    };

    public void addTask(Long id, String name, Long author_id, Long executor_id, String description) {
        try {
            taskRepository.addTask(id, name, author_id, executor_id, description);
            System.out.println("Задача с id=" + id + " добавлена в массив");
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
        return taskRepository.getTaskByStatus(status);
    }

    //Проверка наличия задачи с указанным ID
    public boolean checkTaskById(Long id) {
        return taskRepository.checkTaskById(id);
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
        return taskRepository.getCountTaskByStatus(status);
    }

    public List<Task> getAllTask() {
        return taskRepository.getTaskArray();
    }

    public List<Task> getTasksForMe(Long userId){
        return taskRepository.getTasksForMe(userId);
    }
}
