package com.ada.web.planner.core.dto.task;

import java.time.LocalDateTime;

public record CreateTaskResponseDTO(Long id, String title, String description, LocalDateTime created_at, LocalDateTime due_date, Boolean completed) {
}
