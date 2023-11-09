package com.todoapp02.todoapp02.adapter;

import com.todoapp02.todoapp02.model.TaskGroup;
import com.todoapp02.todoapp02.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {
    @Override
    @Query("from TaskGroup g join fetch g.tasks")  // N+1 odrazu w jednym zapytaniu prosimy zeby wszystkie taski zwiazane z tymi grupami byly pobrane
    List<TaskGroup> findAll();

    @Override
    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
