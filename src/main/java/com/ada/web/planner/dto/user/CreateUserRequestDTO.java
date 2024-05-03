package com.ada.web.planner.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDTO(
        @NotBlank(message = "{name.not.blank}")
        String name,
        @NotBlank(message = "{surname.not.blank}")
        String surname,
        @NotBlank(message = "{login.not.blank}")
        @Email(message = "{login.email}")
        String login,
        @NotBlank(message = "{password.not.blank}")
        String password) {
}
