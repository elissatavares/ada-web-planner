package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.core.model.Task;

import java.time.LocalDateTime;

public interface CreateTask {
    Task create(String title, String description, LocalDateTime due_date);
}
