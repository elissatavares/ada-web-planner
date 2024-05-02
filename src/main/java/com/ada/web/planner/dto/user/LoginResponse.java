package com.ada.web.planner.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginResponse(
        @NotBlank
        @NotNull
        String accessToken,
        UserDTO user) {
}
