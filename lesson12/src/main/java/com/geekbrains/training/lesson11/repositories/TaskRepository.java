package com.geekbrains.training.lesson11.repositories;

import com.geekbrains.training.lesson11.entities.Task;

import java.util.List;

public interface TaskRepository {
    void shutdown();
    boolean checkTaskById(Long id);
    void addTask(Long id, String name, Long author_id, Long executor_id, String description);
    List<Task> getTaskArray();
    void updateTaskStatus(Long idTask, Task.Status newStatus);
    void deleteTask(Long idTask);
    void deleteTask(String nameTask);
    List<Task> getTasksForMe(Long userId);
    int getCountTaskByStatus(Task.Status status);
    List<Task> getTaskByStatus(Task.Status status);
}
