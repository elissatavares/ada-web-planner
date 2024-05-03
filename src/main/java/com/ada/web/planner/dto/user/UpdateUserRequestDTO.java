package com.ada.web.planner.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDTO(@NotBlank(message = "{password.not.blank}")
                                   String password) {
}
