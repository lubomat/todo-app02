package com.todoapp02.todoapp02.adapter;

import com.todoapp02.todoapp02.model.Project;
import com.todoapp02.todoapp02.model.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Integer> {

    @Override
    @Query("from Project p join fetch p.steps")
    List<Project> findAll();

}