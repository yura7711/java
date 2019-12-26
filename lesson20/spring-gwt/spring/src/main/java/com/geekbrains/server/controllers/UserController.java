package com.geekbrains.server.controllers;

import com.geekbrains.gwt.common.TaskAddDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.gwt.common.UserDto;
import com.geekbrains.server.configs.JwtTokenUtil;
import com.geekbrains.server.entities.Task;
import com.geekbrains.server.mappers.StatusMapper;
import com.geekbrains.server.services.TaskService;
import com.geekbrains.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {
    UserService myUserService;

    @Autowired
    public void setUserService(UserService userService) {
        this.myUserService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(myUserService.getAllUsers(), HttpStatus.OK);
    }
}