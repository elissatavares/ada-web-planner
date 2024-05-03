package com.ada.web.planner.service.task;

import com.ada.web.planner.core.exceptions.task.TaskAlreadyCompletedException;
import com.ada.web.planner.core.exceptions.task.TaskNotFoundException;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.usecases.task.UpdateTask;
import com.ada.web.planner.dto.task.TaskDTO;
import com.ada.web.planner.config.hateoas.HateoasTask;
import com.ada.web.planner.infra.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTaskImpl implements UpdateTask {

    private final TaskRepository taskRepository;
    private final HateoasTask hateoasTask;

    public UpdateTaskImpl(TaskRepository taskRepository, HateoasTask hateoasTask) {
        this.taskRepository = taskRepository;
        this.hateoasTask = hateoasTask;
    }

    @Override
    public TaskDTO completed(Long idTask) {
        Task task = validateTaskExist(idTask);

        if (task.getCompleted()) {
            throw new TaskAlreadyCompletedException();
        }
        task.completed();
        Task updatedTask = taskRepository.saveAndFlush(task);
        updatedTask.add(hateoasTask.relAllTasks(),
                hateoasTask.relSelfDeleteLink(task),
                hateoasTask.relSelfReadfLink(updatedTask));

        System.out.println(updatedTask.getCompleted());
        return TaskDTO.toDTO(hateoasTask.hrefTasks(task));
    }

    private Task validateTaskExist(Long idTask) {
        Optional<Task> task = taskRepository.findById(idTask);
        if (task.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return task.get();
    }
}
