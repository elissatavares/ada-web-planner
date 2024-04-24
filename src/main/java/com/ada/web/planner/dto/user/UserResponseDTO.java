package com.ada.web.planner.dto.user;

import com.ada.web.planner.core.model.User;

import java.util.UUID;

public record UserResponseDTO(UUID id, String userName, String email) {
    public static UserResponseDTO toDTO(User user){
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}
