package com.todoapp02.todoapp02.logic;

import com.todoapp02.todoapp02.TaskConfigurationProperties;
import com.todoapp02.todoapp02.model.ProjectRepository;
import com.todoapp02.todoapp02.model.TaskGroupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {
    @Bean
    ProjectService service(
            final ProjectRepository repository,
            final TaskGroupRepository taskGroupRepository,
            final TaskConfigurationProperties config
    ) {
        return new ProjectService(repository, taskGroupRepository, config);
    }
}
