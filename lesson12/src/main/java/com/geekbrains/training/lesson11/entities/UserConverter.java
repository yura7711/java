package com.geekbrains.training.lesson11.entities;

import com.geekbrains.training.lesson11.entities.User;
import com.geekbrains.training.lesson11.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, User> {
    TaskService myTaskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.myTaskService = taskService;
    }

    @Override
    public User convert(String s) {
        Long id = Long.parseLong(s);

        return myTaskService.getUserById(id);
    }
}
