package com.ada.web.planner.dto.user;

import com.ada.web.planner.core.model.User;
import jakarta.validation.constraints.NotBlank;


public record UserDTO(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String login,
        Object links
) {
        public static UserDTO toDTO(User user){
                return new UserDTO(user.getName(), user.getSurname(), user.getLogin(), user.getLinks());
        }
}
