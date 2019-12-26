package com.geekbrains.server.controllers;

import com.geekbrains.gwt.common.TaskAddDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.gwt.common.UserDto;
import com.geekbrains.server.configs.JwtTokenUtil;
import com.geekbrains.server.entities.Task;
import com.geekbrains.server.exceptions.ResourceNotFoundException;
import com.geekbrains.server.mappers.StatusMapper;
import com.geekbrains.server.services.TaskService;
import com.geekbrains.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        if (!myTaskService.existsById(id)) {
            throw new ResourceNotFoundException("Task with id: " + id + " not found");
        }

        return new ResponseEntity<>(myTaskService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam(value = "statusId", required = false) Integer statusId
            , @RequestParam(value = "executer_id", required = false) Long executer_id
            , @RequestParam(value = "author_id", required = false) Long author_id
            ,@RequestHeader("Authorization") String authorization
    ) {
        String userLogin = jwtTokenUtil.extractUsername(authorization.substring(7));
        UserDto userDto = myUserService.findOneByUserLogin(userLogin);
        System.out.println(userDto.getUserId());
        List<TaskDto> tasks = myTaskService.findAllTaskDto(executer_id, author_id, statusId);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<TaskDto.StatusDto>> getStatuses() {
        List<TaskDto.StatusDto> statuses = StatusMapper.MAPPER.fromStatusList(Task.Status.values());
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeTask(@PathVariable Long id) {
        myTaskService.deleteTask(id);
        return new ResponseEntity<String>("Successfully removed", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createTask(@RequestBody @Valid TaskAddDto taskAddDto
            ,BindingResult bindingResult
            ,@RequestHeader("Authorization") String authorization
    ) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError o : bindingResult.getAllErrors()) {
                errorMessage.append(o.getDefaultMessage()).append(";\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        if (taskAddDto.getId() == null) {
            String userLogin = jwtTokenUtil.extractUsername(authorization.substring(7));
            UserDto userDto = myUserService.findOneByUserLogin(userLogin);
            taskAddDto.setAuthor_id(userDto.getUserId());
        }
        myTaskService.addTask(taskAddDto);
        return new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateTask(@RequestBody @Valid TaskAddDto taskAddDto,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError o : bindingResult.getAllErrors()) {
                errorMessage.append(o.getDefaultMessage()).append(";\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        if (taskAddDto.getId() == null) {
            throw new ResourceNotFoundException("Не передан идентификатор задачи");
        }
        myTaskService.addTask(taskAddDto);
        return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
    }
}