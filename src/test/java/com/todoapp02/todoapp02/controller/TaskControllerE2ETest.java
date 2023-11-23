package com.todoapp02.todoapp02.controller;

import com.todoapp02.todoapp02.model.Task;
import com.todoapp02.todoapp02.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {
                            // ten test nie uzywa DB produkcyjnej czyli tak ma byc
    @LocalServerPort             //wstrzykujemy numer portu w momencie uruchomienia app
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;     // mozna odpytywac tak jak postmanem

    @Autowired
    TaskRepository repo;

    @Test
    void httpGet_returnsAllTasks() {
        //given
        int initial = repo.findAll().size();                           // przez migracje
        repo.save(new Task("foo", LocalDateTime.now()));
        repo.save(new Task("bar", LocalDateTime.now()));

        //when
        Task[] result = restTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);

        //then
        assertThat(result).hasSize(initial + 2);

    }


}