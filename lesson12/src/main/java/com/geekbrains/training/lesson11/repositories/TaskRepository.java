package com.geekbrains.training.lesson11.repositories;

import com.geekbrains.training.lesson11.entities.Task;
import com.geekbrains.training.lesson11.entities.User;

import java.util.List;

public interface TaskRepository {
    boolean checkTaskById(Long id);
    void addTask(Long id, String name, Long author_id, Long executor_id, String description);
    void addTask(Task task);
    List<Task> getTaskArray();
    List<User> getUserArray();
    void updateTaskStatus(Long idTask, Task.Status newStatus);
    void deleteTask(Long idTask);
    void deleteTask(String nameTask);
    List<Task> getTasksForMe(Long userId);
    int getCountTaskByStatus(Task.Status status);
    List<Task> getTaskByStatus(Task.Status status);
}
