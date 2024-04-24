package com.ada.web.planner.infra.RestExceptionHandler;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public record RestErrorMessage(@NotBlank @NotNull HttpStatus status, @NotBlank @NotNull String message) {
}
