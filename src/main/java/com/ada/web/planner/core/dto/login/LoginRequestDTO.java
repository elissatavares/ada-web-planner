package com.ada.web.planner.core.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(@NotBlank @NotNull String login, @NotBlank @NotNull String password) {
}
