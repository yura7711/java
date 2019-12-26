package com.geekbrains.server.services;

import com.geekbrains.gwt.common.TaskAddDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.entities.Task;
import com.geekbrains.server.entities.User;
import com.geekbrains.server.mappers.TaskMapper;
import com.geekbrains.server.repositories.TaskRepository;
import com.geekbrains.server.repositories.UserRepository;
import com.geekbrains.server.repositories.specifications.TaskSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
        Task task = null;
        if (taskAddDto.getId() == null) {
            System.out.println("add task");
            taskRepository.save(new Task(taskAddDto, author, executer));
        }
        else{
            System.out.println("edit task");
            task = taskRepository.findById(taskAddDto.getId()).get();
            task = new Task(taskAddDto, author, executer);
            taskRepository.save(task);
        }

        return TaskMapper.MAPPER.fromTask(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).get();
        taskRepository.delete(task);
    }

    public TaskDto getTaskById(Long taskId) {
        return TaskMapper.MAPPER.fromTask(taskRepository.findById(taskId).get());
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

        return TaskMapper.MAPPER.fromTaskList(tasks);
    }

    public TaskDto findById(Long id) {
        return TaskMapper.MAPPER.fromTask(taskRepository.findById(id).get());
    }

    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }
}
