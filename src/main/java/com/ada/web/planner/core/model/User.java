package com.ada.web.planner.core.model;

import com.ada.web.planner.dto.user.CreateUserRequestDTO;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String surname;

    private String login;

    private String password;

    public User() {

    }

    public User(CreateUserRequestDTO requestDTO) {
        this.name = requestDTO.name();
        this.surname = requestDTO.surname();
        this.login = requestDTO.login();
        this.password = requestDTO.password();
    }



    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
