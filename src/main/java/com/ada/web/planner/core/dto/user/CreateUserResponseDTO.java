package com.ada.web.planner.core.dto.user;

import com.ada.web.planner.core.model.User;

import java.util.UUID;

public record CreateUserResponseDTO(UUID id, String name, String surname, String login) {

    public static CreateUserResponseDTO toDTO(User user){
        return new CreateUserResponseDTO(user.getId(), user.getName(), user.getSurname(), user.getLogin());
    }
}
