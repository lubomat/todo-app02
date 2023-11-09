package com.todoapp02.todoapp02.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "task_groups")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // zmieniam na AUTO i nie dziala ?
    private int id;
    @NotBlank(message = "Task's description must be not null")
    private String description;
    private boolean done;

   // @OneToMany(fetch = FetchType.LAZY)   // leniwie dociągamy wtedy kiedy jest to potrzebne
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")  // usuwając/zapisując grupe usuwamy/zapisujemy wszystkie jej taski
    private Set<Task> tasks;
    @ManyToOne                              // wiele taskow moze trafic do jednej grupy
    @JoinColumn(name = "project_id")     // po tej kolumnie dołączamy dane z group
    private Project project;

    public TaskGroup() {
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    Project getProject() {
        return project;
    }

    void setProject(Project project) {
        this.project = project;
    }
}
