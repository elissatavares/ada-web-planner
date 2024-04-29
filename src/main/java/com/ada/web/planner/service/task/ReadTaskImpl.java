package com.ada.web.planner.service.task;

import com.ada.web.planner.core.exceptions.task.TaskNotFoundException;
import com.ada.web.planner.core.exceptions.task.UserHasNoTasksException;
import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.task.ReadTask;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.infra.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadTaskImpl implements ReadTask {

    private final TaskRepository repository;
    private final ReadUser readUserService;

    @Autowired
    public ReadTaskImpl(TaskRepository repository, ReadUser readUserService) {
        this.repository = repository;
        this.readUserService = readUserService;
    }

    @Override
    public Task read(Long id, String login) {
        User user = readUserService.read(login);
        return validateTaskExist(id);
    }

    @Override
    public List<Task> readALl(String login) {
        User user = readUserService.read(login);
        List<Task> taskList = validateTaskListExist(user);
        return taskList;
    }

    //refatorar: codigo repetido nas outras classes do service
    private Task validateTaskExist(Long id){
        Optional<Task> task = repository.findById(id);
        if(task.isEmpty()){
            throw new TaskNotFoundException();
        }
        return task.get();
    }

    //refatorar: codigo repetido nas outras classes do service
    private List<Task> validateTaskListExist(User user){
        List<Task> taskList = repository.findAllByUserId(user);
        if(taskList.isEmpty()){
            throw new UserHasNoTasksException();
        }
        return taskList;
    }

}
