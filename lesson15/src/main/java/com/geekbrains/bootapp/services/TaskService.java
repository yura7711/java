package com.geekbrains.bootapp.services;

import com.geekbrains.bootapp.entities.Task;
import com.geekbrains.bootapp.repositories.TaskRepository;
import com.geekbrains.bootapp.repositories.specifications.TaskSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).get();
    }

    public Page<Task> getAllTasks(Pageable pageable, Long executer_id, Long author_id, Task.Status status) {
        Specification<Task> spec = Specification.where(null);

        if (executer_id != null) {
            spec = spec.and(TaskSpecifications.executerEquals(executer_id));
        }
        if (author_id != null) {
            spec = spec.and(TaskSpecifications.authorEquals(author_id));
        }
        if (status != null) {
            spec = spec.and(TaskSpecifications.statusEquals(status));
        }

        return taskRepository.findAll(spec, pageable);
    }
}
