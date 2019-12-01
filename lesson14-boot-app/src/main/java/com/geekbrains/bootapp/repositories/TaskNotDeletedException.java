package com.geekbrains.bootapp.repositories;

public class TaskNotDeletedException extends RepositoryExceptions {
    public TaskNotDeletedException(Long taskId) {
        super("Задача c id=" + taskId + " не была удалена");
    }

    public TaskNotDeletedException(String taskName) {
        super("Задача c именем \"" + taskName + "\" не была удалена");
    }
}
