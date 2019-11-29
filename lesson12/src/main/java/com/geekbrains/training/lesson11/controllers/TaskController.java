package com.geekbrains.training.lesson11.controllers;

import com.geekbrains.training.lesson11.entities.Task;
import com.geekbrains.training.lesson11.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    TaskService myTaskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.myTaskService = taskService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String showAllTasks(Model model) {
        List<Task> tasks = myTaskService.getAllTask();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping(path = "/add")
    public String addTask(Model model) {
        Task task = new Task();

        model.addAttribute("task", task);
        model.addAttribute("users", myTaskService.getAllUsers());
        return "task_form";
    }

    @PostMapping(path = "/add")
    public String saveTask(@ModelAttribute("task") Task task) {
        task.setStatus(Task.Status.created);
        myTaskService.addTask(task);
        return "redirect:/tasks/";
    }
}