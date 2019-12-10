package com.geekbrains.bootapp.repositories;

public class TaskNotUpdatedException extends RepositoryExceptions {
    public TaskNotUpdatedException(Long taskId) {
        super("Задача c id=" + taskId + " не была обновлена");
    }
}
