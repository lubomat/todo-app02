package com.todoapp02.todoapp02.model;


import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseAuditableEntity {


    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;


    @PrePersist                     // operacja wykona sie przed zapisem do DB
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preMerge() {
        updatedOn = LocalDateTime.now();
    }
}
