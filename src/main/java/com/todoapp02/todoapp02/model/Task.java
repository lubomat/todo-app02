package com.todoapp02.todoapp02.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // zmieniam na AUTO i nie dziala ?
    private int id;
    @NotBlank(message = "Task's description must be not null")

    private String description;
    private boolean done;
    private LocalDateTime deadline;
    @Embedded                               // osadzamy w ten sposob w tym miejscu klase @Embedable
    private Audit audit = new Audit();
    @ManyToOne                              // wiele taskow moze trafic do jednej grupy
    @JoinColumn(name = "task_group_id")     // po tej kolumnie dołączamy dane z group
    private TaskGroup group;


    Task() {
    }
                                            // dzieki temu można utworzyc task razem z grupą
    public Task(String description, LocalDateTime deadline) {
        this(description, deadline, null);
    }

    public Task(String description, LocalDateTime deadline, TaskGroup group) {
        this.description = description;
        this.deadline = deadline;
        if (group != null) {
            this.group = group;
        }
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

    public LocalDateTime getDeadline() {
        return deadline;
    }

    void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }


    TaskGroup getGroup() {
        return group;
    }

    void setGroup(TaskGroup group) {
        this.group = group;
    }

    public void updateFrom(final Task source) {   // metoda ktora chowa pola klasy w klasie i nie udostepnia ich na zewnatrz
        description = source.description;         // lepiej udostepniac metody
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }

}
