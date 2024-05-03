package com.ada.web.planner.service.task;

import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.usecases.task.CreateTask;
import com.ada.web.planner.dto.task.TaskDTO;
import com.ada.web.planner.config.hateoas.HateoasTask;
import com.ada.web.planner.infra.repository.TaskRepository;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateTaskImpl implements CreateTask {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final HateoasTask hateoasTask;

    public CreateTaskImpl(TaskRepository taskRepository, UserRepository userRepository, HateoasTask hateoasTask) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.hateoasTask = hateoasTask;
    }

    @Override
    public TaskDTO create(Task task, UUID idUser) {
        task.setCreated_at(LocalDateTime.now());
        task.setUser_id(userRepository.getById(idUser));
        System.out.println(task.getTitle());
        taskRepository.save(task);

        return TaskDTO.toDTO(hateoasTask.hrefTasks(task));
    }
}
