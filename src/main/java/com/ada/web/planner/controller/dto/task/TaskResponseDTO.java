package com.ada.web.planner.controller.dto.task;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TaskResponseDTO(Long id, String title, String description, LocalDateTime created_at, LocalDateTime due_date, Boolean completed) {
}
