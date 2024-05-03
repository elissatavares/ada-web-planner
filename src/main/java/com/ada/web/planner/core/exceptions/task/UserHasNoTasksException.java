package com.ada.web.planner.core.exceptions.task;

import org.springframework.http.HttpStatus;

public class UserHasNoTasksException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public UserHasNoTasksException() {
        super("The user has no tasks.");
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
