package com.geekbrains.training.lesson11.repositories;

public class TaskNotUpdatedException extends RepositoryExceptions {
    public TaskNotUpdatedException(Long taskId) {
        super("Задача c id=" + taskId + " не была обновлена");
    }
}
