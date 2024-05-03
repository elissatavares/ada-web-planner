package com.ada.web.planner.core.exceptions.task;

import org.springframework.http.HttpStatus;

public class TaskAlreadyCompletedException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public TaskAlreadyCompletedException() {
        super("The task has already been completed.");
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
