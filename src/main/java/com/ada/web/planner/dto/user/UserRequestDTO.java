package com.ada.web.planner.dto.user;

import com.ada.web.planner.core.model.User;

public record UserRequestDTO(String userName, String email, String password) {

    public User entityUser(){
        return new User(this.userName, this.email, this.password);
    }
}
