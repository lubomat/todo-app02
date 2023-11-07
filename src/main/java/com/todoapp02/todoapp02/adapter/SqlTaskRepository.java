package com.todoapp02.todoapp02.adapter;

import com.todoapp02.todoapp02.model.Task;
import com.todoapp02.todoapp02.model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from tasks where id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);   // pozwala po nie zamknietym
                                                               // zadaniu z grupa o zadanym
                                                               // id odfiltrowac
}
