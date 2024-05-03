package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.dto.task.TaskDTO;

public interface DeleteTask {
    TaskDTO delete(Long idTask);
}
