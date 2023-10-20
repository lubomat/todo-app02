package com.todoapp02.todoapp02.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface TaskRepository extends JpaRepository<Task, Integer> {
}
