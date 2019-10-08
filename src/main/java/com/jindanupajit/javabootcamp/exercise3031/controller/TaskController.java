package com.jindanupajit.javabootcamp.exercise3031.controller;

import com.jindanupajit.javabootcamp.exercise3031.entity.Task;
import com.jindanupajit.javabootcamp.exercise3031.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;


    @GetMapping(value = {"/","/task","/tasks","/task/all"})
    public String taskAllPage(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "task_list";
    }

    @GetMapping("/task/{id}")
    public String taskIdPage(@PathVariable("id") long id, Model model ) {
        model.addAttribute("tasks", taskRepository.findAllById(Stream.of(id)
                .collect(Collectors.toList())));
        return "task_list";
    }

    @GetMapping("/task/{id}/delete")
    public String taskIdDelete(@PathVariable("id") long id) {
        taskRepository.deleteById(id);
        return "redirect:/task/all";
    }

    @GetMapping("/task/{id}/update/form")
    public String taskIdUpdateFormPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("task",taskRepository.findById(id).get());
        return "input_form";
    }

    @GetMapping("/task/create/form")
    public String taskCreateFormPage(Model model) {
        model.addAttribute("task", new Task());
        return "input_form";
    }

    @PostMapping("/task/mutate")
    public String taskMutate(@Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "input_form";
        }

        taskRepository.save(task);
        return "redirect:/task/all";
    }
}
