package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.dto.task.TaskDTO;

import java.util.UUID;

public interface CreateTask {
    TaskDTO create(Task task, UUID idUser);
}
