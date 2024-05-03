package com.ada.web.planner.service.task;

import com.ada.web.planner.core.exceptions.task.TaskNotFoundException;
import com.ada.web.planner.core.exceptions.task.UserHasNoTasksException;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.task.ReadTask;
import com.ada.web.planner.dto.task.TaskDTO;
import com.ada.web.planner.config.hateoas.HateoasTask;
import com.ada.web.planner.infra.repository.TaskRepository;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ReadTaskImpl implements ReadTask {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final HateoasTask hateoasTask;

    public ReadTaskImpl(TaskRepository repository, UserRepository userRepository, HateoasTask hateoasTask) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.hateoasTask = hateoasTask;
    }

    @Override
    public TaskDTO read(Long idTask) {
        Optional<Task> task = repository.findById(idTask);
        if(task.isEmpty()){
            throw new TaskNotFoundException();
        }

        task.get().add(hateoasTask.relAllTasks(),
                hateoasTask.relSelfCompleteLink(task.get()),
                hateoasTask.relSelfDeleteLink(task.get()));

        return TaskDTO.toDTO(task.get());
    }

    @Override
    public List<TaskDTO> readALl(UUID idUser) {
        User user = userRepository.getById(idUser);
        List<Task> taskList = repository.findAllByUserId(user);
        if(taskList.isEmpty()){
            throw new UserHasNoTasksException();
        }

        List<Task> taskListHateoas = hateoasTask.relSelfTask(taskList);

        return taskListHateoas.stream().map(TaskDTO::toDTO).toList();
    }

}
