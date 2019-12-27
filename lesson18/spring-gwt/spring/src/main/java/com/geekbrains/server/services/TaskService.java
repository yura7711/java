package com.geekbrains.server.services;

import com.geekbrains.gwt.common.TaskAddDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.entities.Task;
import com.geekbrains.server.entities.User;
import com.geekbrains.server.repositories.TaskRepository;
import com.geekbrains.server.repositories.UserRepository;
import com.geekbrains.server.repositories.specifications.TaskSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TaskDto addTask(TaskAddDto taskAddDto) {
        User author = userRepository.findById(taskAddDto.getAuthor_id()).get();
        User executer = userRepository.findById(taskAddDto.getExecutor_id()).get();

        Task task = taskRepository.save(new Task(taskAddDto, author, executer));

        return new TaskDto(task.getId()
                ,task.getName()
                ,task.getAuthor().getUserName()
                ,task.getExecutor().getUserName()
                ,task.getDescription()
                ,task.getStatus().getRusTitle()
        );
    }

    public void deleteTask(Long id) {
        for (Task o: taskRepository.findAll()) {
            if (o.getId() == id){
                taskRepository.delete(o);
            }
        }
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).get();
    }

    public List<TaskDto> findAllTaskDto(Long executer_id, Long author_id, Integer statusId) {
        Specification<Task> spec = Specification.where(null);

        if (executer_id != null) {
            spec = spec.and(TaskSpecifications.executerEquals(executer_id));
        }
        if (author_id != null) {
            spec = spec.and(TaskSpecifications.authorEquals(author_id));
        }
        if (statusId != null) {
            spec = spec.and(TaskSpecifications.statusEquals(statusId));
        }
        List<Task> tasks = taskRepository.findAll(spec);

        List<TaskDto> tasksDto = new ArrayList<>();

        for (Task task: tasks) {
            tasksDto.add(new TaskDto(task.getId(), task.getName(), task.getAuthor().getUserName(), task.getExecutor().getUserName(), task.getDescription(), task.getStatus().getRusTitle()));
        }

        return tasksDto;
    }
}
