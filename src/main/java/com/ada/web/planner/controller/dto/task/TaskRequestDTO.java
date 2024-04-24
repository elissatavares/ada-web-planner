package com.ada.web.planner.controller.dto.task;


import java.time.LocalDateTime;

public record TaskRequestDTO(String id, String title, String description, LocalDateTime due_date) {
}
