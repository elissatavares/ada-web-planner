package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.core.model.Task;

public interface DeleteTask {
    Task delete(Long id, String login);
}
