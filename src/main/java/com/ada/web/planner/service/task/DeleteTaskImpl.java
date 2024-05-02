package com.ada.web.planner.service.task;

import com.ada.web.planner.core.exceptions.task.TaskNotFoundException;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.usecases.task.DeleteTask;
import com.ada.web.planner.dto.task.TaskDTO;
import com.ada.web.planner.config.hateoas.HateoasTask;
import com.ada.web.planner.infra.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteTaskImpl implements DeleteTask {

    private final TaskRepository repository;
    private final HateoasTask hateoasTask;

    public DeleteTaskImpl(TaskRepository repository, HateoasTask hateoasTask) {
        this.repository = repository;
        this.hateoasTask = hateoasTask;
    }

    @Override
    public TaskDTO delete(Long idTask) {
        Optional<Task> task = repository.findById(idTask);
        if(task.isEmpty()){
            throw new TaskNotFoundException();
        }
        repository.delete(task.get());

        return TaskDTO.toDTO(task.get().add(hateoasTask.relAllTasks()));
    }
}
