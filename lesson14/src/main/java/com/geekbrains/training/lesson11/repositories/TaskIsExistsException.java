package com.geekbrains.training.lesson11.repositories;

public class TaskIsExistsException extends RepositoryExceptions {
    private Long taskId;

    public TaskIsExistsException(Long taskId) {
        super("Задача с id=" + taskId + " уже существует. Добавление не выполнено!");
        this.taskId = taskId;
    }
}
