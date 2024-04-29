package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.core.model.Task;

public interface CreateTask {
    Task create(Task task, String login);
}
