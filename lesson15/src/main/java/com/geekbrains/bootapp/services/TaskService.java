package com.geekbrains.bootapp.services;

import com.geekbrains.bootapp.entities.Task;
import com.geekbrains.bootapp.entities.User;
import com.geekbrains.bootapp.repositories.OracleDBRepository;
import com.geekbrains.bootapp.repositories.RepositoryExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private OracleDBRepository taskRepository;

    @Autowired
    public void setTaskRepository(OracleDBRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Long id, String name, Long author_id, Long executor_id, String description) {
        try {
            taskRepository.addTask(id, name, author_id, executor_id, description);
            System.out.println("Задача с id=" + id + " добавлена в массив");
        } catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTask(Task task) {
        try {
            taskRepository.addTask(task);
            System.out.println("Задача с id=" + task.getId() + " добавлена в массив");
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

    public Task getTaskById(Long taskId) {
        return taskRepository.getTaskById(taskId);
    }

    //Проверка наличия задачи с указанным ID
    public boolean checkTaskById(Long id) {
        return taskRepository.checkTaskById(id);
    }

    //Подсчет количества задач по определенному статусу
    public int getCountTaskByStatus(Task.Status status) {
        return taskRepository.getCountTaskByStatus(status);
    }

    public List<Task> getTasks(Task.Status status, Long executer_id, Long author_id) {
        return taskRepository.getTaskArray(status, executer_id, author_id);
    }

    public List<User> getAllUsers() {
        return taskRepository.getUserArray();
    }

    public List<Task> getTasksForMe(Long userId){
        return taskRepository.getTasksForMe(userId);
    }
}
