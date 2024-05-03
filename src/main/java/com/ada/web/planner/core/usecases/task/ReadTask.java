package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.dto.task.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface ReadTask {

    TaskDTO read(Long idTask);

    List<TaskDTO> readALl(UUID idUser);
}
