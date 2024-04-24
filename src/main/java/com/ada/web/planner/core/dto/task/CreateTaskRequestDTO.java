package com.ada.web.planner.core.dto.task;


import java.time.LocalDateTime;

public record CreateTaskRequestDTO(String id, String title, String description, LocalDateTime due_date) {
}
