package com.ada.web.planner.core.usecases.task;

import com.ada.web.planner.core.model.Task;

import java.util.List;

public interface ReadTask {

    Task read(Long id, String login);

    List<Task> readALl(String login);
}
