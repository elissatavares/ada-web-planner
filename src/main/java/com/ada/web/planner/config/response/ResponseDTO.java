package com.ada.web.planner.config.response;

import jakarta.validation.constraints.NotBlank;

public record ResponseDTO(
        @NotBlank String message,
        int code,
        @NotBlank String status,
        Object object) {
}
