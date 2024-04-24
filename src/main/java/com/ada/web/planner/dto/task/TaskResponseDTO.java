package com.ada.web.planner.dto.task;

import java.time.LocalDateTime;

public record TaskResponseDTO(Long id, String title, String description, LocalDateTime created_at, LocalDateTime due_date, Boolean completed) {
}
