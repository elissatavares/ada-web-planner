package com.ada.web.planner.service.task;

import com.ada.web.planner.core.model.Task;
import com.ada.web.planner.core.usecases.task.CreateTask;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class CreateTaskImpl implements CreateTask {
    @Override
    public Task create(String title, String description, LocalDateTime due_date) {
        Task task = new Task(title, description, LocalDateTime.now(), due_date);
        return task;
    }
}
