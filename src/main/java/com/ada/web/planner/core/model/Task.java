package com.ada.web.planner.core.model;

import com.ada.web.planner.dto.task.CreateTaskRequestDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    private String title;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime due_date;
    private Boolean completed;

    public Task(CreateTaskRequestDTO taskRequestDTO) {
        this.title = taskRequestDTO.title();
        this.description = taskRequestDTO.description();
        this.due_date = taskRequestDTO.due_date();
        this.completed = false;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUser_id(User user_id) {
        this.userId = user_id;
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void completed() {
        this.completed = true;
    }

    public Task() {

    }
}
