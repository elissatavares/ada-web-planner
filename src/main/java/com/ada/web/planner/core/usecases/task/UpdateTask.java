package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.core.model.Task;

public interface UpdateTask {
    Task completed(Long id, String login);
}
