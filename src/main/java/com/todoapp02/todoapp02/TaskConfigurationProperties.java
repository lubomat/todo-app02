package com.todoapp02.todoapp02;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("task")
public class TaskConfigurationProperties {
    private boolean allowMultipleTaskFromTemplate;

    public boolean isAllowMultipleTaskFromTemplate() {
        return allowMultipleTaskFromTemplate;
    }

    public void setAllowMultipleTaskFromTemplate(boolean allowMultipleTaskFromTemplate) {
        this.allowMultipleTaskFromTemplate = allowMultipleTaskFromTemplate;
    }
}
