package com.ada.web.planner.service.task;

import com.ada.web.planner.core.exceptions.task.TaskAlreadyCompletedException;
import com.ada.web.planner.core.exceptions.task.TaskNotFoundException;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.task.UpdateTask;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.infra.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTaskImpl implements UpdateTask {

    private final TaskRepository repository;
    private final ReadUser readUserService;

    @Autowired
    public UpdateTaskImpl(TaskRepository taskRepository, ReadUser readUserService) {
        this.repository = taskRepository;
        this.readUserService = readUserService;
    }

    @Override
    public Task completed(Long id, String login) {
        User user = readUserService.read(login);
        Task task = validateTaskExist(id);
        validateTaskCompleted(task);
        task.completed();
        return repository.saveAndFlush(task);
    }

    //refatorar: codigo repetido nas outras classes do service
    private Task validateTaskExist(Long id){
        Optional<Task> task = repository.findById(id);
        if(task.isEmpty()){
            throw new TaskNotFoundException();
        }
        return task.get();
    }

    private void validateTaskCompleted(Task task){
        if(task.getCompleted()){
            throw new TaskAlreadyCompletedException();
        }
    }
}
