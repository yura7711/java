package com.geekbrains.training.lesson11.controllers;

import com.geekbrains.training.lesson11.entities.Task;
import com.geekbrains.training.lesson11.entities.User;
import com.geekbrains.training.lesson11.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TaskController {
    //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskConfig.class);
    TaskService myTaskService;// = context.getBean(TaskService.class);

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.myTaskService = taskService;
    }

    @RequestMapping(path = "/show", method = RequestMethod.GET)
    public String showAllTasks(Model model) {
        List<Task> tasks = myTaskService.getAllTask();

        model.addAttribute("tasks", tasks);
        return "index";
    }

    @RequestMapping(path = "/task_form", method = RequestMethod.GET)
    public String addTask(Model model) {
        Task task = new Task();
        task.setId(777L);
        model.addAttribute("task", task);
        model.addAttribute("users", myTaskService.getAllUsers());
        return "task_form";
    }

    @PostMapping(path = "/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        myTaskService.addTask(task);
        return "index";
    }
}