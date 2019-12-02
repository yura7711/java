package com.geekbrains.bootapp.repositories;

public class TaskIsExistsException extends RepositoryExceptions {
    private Long taskId;

    public TaskIsExistsException(Long taskId) {
        super("Задача с id=" + taskId + " уже существует. Добавление не выполнено!");
        this.taskId = taskId;
    }
}
