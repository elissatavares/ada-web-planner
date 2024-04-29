package com.ada.web.planner.service.task;

import com.ada.web.planner.core.exceptions.task.TaskNotFoundException;
import com.ada.web.planner.core.exceptions.user.ExistingUserException;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.task.DeleteTask;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.infra.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteTaskImpl implements DeleteTask {

    private final TaskRepository repository;
    private final ReadUser readUserService;

    @Autowired
    public DeleteTaskImpl(TaskRepository repository, ReadUser readUserService) {
        this.repository = repository;
        this.readUserService = readUserService;
    }

    @Override
    public Task delete(Long id, String login) {
        User user = readUserService.read(login);
        Task task = validateTaskExist(id);
        repository.delete(task);
        return task;
    }

    //refatorar: codigo repetido nas outras classes do service
    private Task validateTaskExist(Long id){
        Optional<Task> task = repository.findById(id);
        if(task.isEmpty()){
            throw new TaskNotFoundException();
        }
        return task.get();
    }
}
