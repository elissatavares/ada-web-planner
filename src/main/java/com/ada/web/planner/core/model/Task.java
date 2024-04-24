package com.ada.web.planner.core.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime due_date;
    private Boolean completed;

    public Task(String title, String description, LocalDateTime created_at, LocalDateTime due_date) {
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.due_date = due_date;
        this.completed = false;
    }

    public Task() {

    }
}
