package com.ada.web.planner.core.model;

import com.ada.web.planner.controller.dto.user.UserRequestDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "user")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String email;

    private LocalDateTime created_at;

    private String password;

    public User() {

    }
    public User(UserRequestDTO requestDTO) {
        this.username = requestDTO.userName();
        this.email = requestDTO.email();
        this.password = requestDTO.password();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", created_at=" + created_at +
                ", password='" + password + '\'' +
                '}';
    }

    public void setCreated_at(LocalDateTime localDateTime) {
        this.created_at = localDateTime;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
