package com.geekbrains.bootapp.repositories;

import com.geekbrains.bootapp.entities.Task;
import com.geekbrains.bootapp.entities.User;

import java.util.List;

public interface TaskRepository {
    boolean checkTaskById(Long id);
    void addTask(Long id, String name, Long author_id, Long executor_id, String description);
    void addTask(Task task);
    List<Task> getTaskArray(Task.Status status, Long executer_id, Long author_id);
    List<User> getUserArray();
    void updateTaskStatus(Long idTask, Task.Status newStatus);
    void deleteTask(Long idTask);
    void deleteTask(String nameTask);
    List<Task> getTasksForMe(Long userId);
    int getCountTaskByStatus(Task.Status status);
    Task getTaskById(Long taskId);
    List<Task> getTaskByStatus(Task.Status status);
}
