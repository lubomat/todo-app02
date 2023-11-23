package com.todoapp02.todoapp02.logic;

import com.todoapp02.todoapp02.TaskConfigurationProperties;
import com.todoapp02.todoapp02.model.ProjectRepository;
import com.todoapp02.todoapp02.model.TaskGroupRepository;
import com.todoapp02.todoapp02.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {
    @Bean                                        // @Service  ProjectService
    ProjectService projectService(
            final ProjectRepository repository,
            final TaskGroupRepository taskGroupRepository,
            final TaskGroupService taskGroupService,
            final TaskConfigurationProperties config
    ) {
        return new ProjectService(repository, taskGroupRepository, config, taskGroupService);
    }

    @Bean
    TaskGroupService taskGroupService(
            final TaskGroupRepository repository,
            final TaskRepository taskRepository
    ) {
        return new TaskGroupService(repository, taskRepository);
    }
}
