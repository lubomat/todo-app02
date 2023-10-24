package com.todoapp02.todoapp02.controller;

import com.todoapp02.todoapp02.model.TaskRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public class TaskController {

    private final TaskRepository repository;

    TaskController(TaskRepository repository) {
        this.repository = repository;
    }


}
