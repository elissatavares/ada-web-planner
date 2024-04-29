package com.ada.web.planner.service.task;

import com.ada.web.planner.core.exceptions.user.ExistingUserException;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.task.CreateTask;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.infra.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class CreateTaskImpl implements CreateTask {

    private final TaskRepository taskRepository;
    private final ReadUser readUserService;

    @Autowired
    public CreateTaskImpl(TaskRepository taskRepository, ReadUser readUserService) {
        this.taskRepository = taskRepository;
        this.readUserService = readUserService;
    }

    @Override
    public Task create(Task task, String login) {
        User user = readUserService.read(login);
        task.setCreated_at(LocalDateTime.now());
        task.setUser_id(user);
        taskRepository.save(task);
        return task;
    }
}
