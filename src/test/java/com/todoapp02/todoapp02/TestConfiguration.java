package com.todoapp02.todoapp02;

import com.todoapp02.todoapp02.model.Task;
import com.todoapp02.todoapp02.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Configuration
public class TestConfiguration {
    @Bean
    @Profile("integration")              //{,"!prod"}      // zadziała tylko na profilu integration
    TaskRepository testRepo() {                       // jestesmy niezalezni od DB, Repo symuluje DB
        return new TaskRepository() {
            private Map<Integer, Task> tasks = new HashMap<>();

            @Override
            public List<Task> findAll () {
                return new ArrayList<>(tasks.values());
            }

            @Override
            public Page<Task> findAll (Pageable page){
                return null;
            }

            @Override
            public Optional<Task> findById (Integer id){
                return Optional.ofNullable(tasks.get(id));
            }

            @Override
            public boolean existsById (Integer id){
                return tasks.containsKey(id);
            }

            @Override
            public boolean existsByDoneIsFalseAndGroup_Id (Integer groupId){
                return false;
            }

            @Override
            public List<Task> findByDone ( boolean done){
                return null;
            }

            @Override
            public Task save (Task entity){
                return tasks.put(tasks.size() + 1, entity);
            }
        };
    }
}
