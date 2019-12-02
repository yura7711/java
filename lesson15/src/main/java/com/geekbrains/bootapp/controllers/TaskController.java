package com.geekbrains.bootapp.controllers;

import com.geekbrains.bootapp.entities.Task;
import com.geekbrains.bootapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    TaskService myTaskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.myTaskService = taskService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String showAllTasks(Model model
            ,@RequestParam(value = "statusParam", required = false) Task.Status statusParam
            ,@RequestParam(value = "executer_id", required = false) Long executer_id
            ,@RequestParam(value = "author_id", required = false) Long author_id
    ) {
        List<Task> tasks = myTaskService.getTasks(statusParam, executer_id, author_id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("statusParam", statusParam);
        model.addAttribute("executer_id", executer_id);
        model.addAttribute("author_id", author_id);
        model.addAttribute("users", myTaskService.getAllUsers());
        model.addAttribute("statuses", Task.Status.values());
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
        return "redirect:/";
    }

    @GetMapping(path = "/taskDetail")
    public String showTaskDetail(Model model, @RequestParam(value = "taskId", required = false) Long taskId) {
        Task task = myTaskService.getTaskById(taskId);

        model.addAttribute("task", task);
        return "taskDetail";
    }
}