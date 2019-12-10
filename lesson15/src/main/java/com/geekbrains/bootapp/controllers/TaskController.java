package com.geekbrains.bootapp.controllers;

import com.geekbrains.bootapp.entities.Task;
import com.geekbrains.bootapp.services.TaskService;
import com.geekbrains.bootapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
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

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String showAllTasks(Model model
            ,@RequestParam(defaultValue = "1") Long pageNumber
            ,@RequestParam(value = "statusParam", required = false) Task.Status status
            ,@RequestParam(value = "executer_id", required = false) Long executer_id
            ,@RequestParam(value = "author_id", required = false) Long author_id
    ) {
        int productsPerPage = 5;
        if (pageNumber < 1L) {
            pageNumber = 1L;
        }

        Page<Task> tasks = myTaskService.getAllTasks(PageRequest.of(pageNumber.intValue() - 1, productsPerPage, Sort.Direction.ASC, "id")
                                                    ,executer_id
                                                    ,author_id
                                                    ,status
                                                    );

        model.addAttribute("tasks", tasks);
        model.addAttribute("statusParam", status);
        model.addAttribute("executer_id", executer_id);
        model.addAttribute("author_id", author_id);
        model.addAttribute("users", myUserService.getAllUsers());
        model.addAttribute("statuses", Task.Status.values());
        return "index";
    }

    @GetMapping(path = "/add")
    public String addTask(Model model) {
        Task task = new Task();

        model.addAttribute("task", task);
        model.addAttribute("users", myUserService.getAllUsers());
        return "task_form";
    }

    @PostMapping(path = "/add")
    public String saveTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("users", myUserService.getAllUsers());
            return "task_form";
        }
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