package com.ada.web.planner.dto.task;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateTaskRequestDTO(@NotBlank(message = "{title.not.blank}") String title,
                                   String description,
                                   LocalDateTime due_date) {
}
