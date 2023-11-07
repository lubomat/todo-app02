package com.todoapp02.todoapp02.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_groups")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // zmieniam na AUTO i nie dziala ?
    private int id;
    @NotBlank(message = "Task's description must be not null")
    private String description;
    private boolean done;
    @Embedded                      // osadzamy w ten sposob w tym miejscu klase @Embedable
    private Audit audit = new Audit();

    TaskGroup() {
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

}
