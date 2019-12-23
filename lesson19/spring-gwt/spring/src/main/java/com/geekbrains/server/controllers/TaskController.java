package com.geekbrains.server.controllers;

import com.geekbrains.gwt.common.TaskAddDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.configs.JwtTokenUtil;
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
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.myTaskService = taskService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.myUserService = userService;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/{id}")
    public TaskAddDto getItem(@PathVariable Long id) {
        return myTaskService.findById(id);
    }

    @GetMapping
    public List<TaskDto> getAllTasks(@RequestParam(value = "statusId", required = false) Integer statusId
            , @RequestParam(value = "executer_id", required = false) Long executer_id
            , @RequestParam(value = "author_id", required = false) Long author_id
            ,@RequestHeader("Authorization") String authorization
    ) {
        String userLogin = jwtTokenUtil.extractUsername(authorization.substring(7));
        User user = myUserService.findOneByUserLogin(userLogin);
        System.out.println(user.getUserId());
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
    public ResponseEntity<String> createTask(@RequestBody TaskAddDto taskAddDto
            ,@RequestHeader("Authorization") String authorization
    ) {
        if (taskAddDto.getId() == null) {
            String userLogin = jwtTokenUtil.extractUsername(authorization.substring(7));
            User user = myUserService.findOneByUserLogin(userLogin);
            taskAddDto.setAuthor_id(user.getUserId());
        }
        myTaskService.addTask(taskAddDto);
        return new ResponseEntity<String>("Successfully created", HttpStatus.OK);
    }
}