package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.dto.task.TaskDTO;


public interface UpdateTask {
    TaskDTO completed(Long id);
}
