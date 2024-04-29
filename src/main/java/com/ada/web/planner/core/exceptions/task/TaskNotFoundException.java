package com.ada.web.planner.core.exceptions.task;

import org.springframework.http.HttpStatus;

public class TaskNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public TaskNotFoundException() {
        super("The task does not exist.");
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
