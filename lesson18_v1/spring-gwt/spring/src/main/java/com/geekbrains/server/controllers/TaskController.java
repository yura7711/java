package com.geekbrains.server.controllers;

import com.geekbrains.gwt.common.TaskAddDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.entities.Task;
import com.geekbrains.server.entities.User;
import com.geekbrains.server.services.TaskService;
import com.geekbrains.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/tasks")
public class TaskController {
    TaskService myTaskService;
    UserService myUserService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.myTaskService = taskService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.myUserService = userService;
    }

    @GetMapping("/")
    public List<TaskDto> getAllItems(@RequestParam(value = "statusId", required = false) Integer statusId
            , @RequestParam(value = "executer_id", required = false) Long executer_id
            , @RequestParam(value = "author_id", required = false) Long author_id
    ) {
        List<TaskDto> tasks = myTaskService.findAllTaskDto(executer_id, author_id, statusId);
        return tasks;
    }

    @GetMapping("/statuses")
    public List<Task.Status> getStatuses() {
        List<Task.Status> statuses = new ArrayList<>();
        for (Task.Status o: Task.Status.values()) {
            statuses.add(o);
        }
        return statuses;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return myUserService.getAllUsers();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeTask(@PathVariable Long id) {
        myTaskService.deleteTask(id);
        return new ResponseEntity<String>("Successfully removed", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createTask(@ModelAttribute TaskAddDto taskAddDto) {
        myTaskService.addTask(taskAddDto);
        return new ResponseEntity<String>("Successfully created", HttpStatus.OK);
    }
}