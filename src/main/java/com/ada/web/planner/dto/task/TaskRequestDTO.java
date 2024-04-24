package com.ada.web.planner.dto.task;

import java.time.LocalDateTime;

public record TaskRequestDTO(String title, String description, LocalDateTime due_date) {
}
