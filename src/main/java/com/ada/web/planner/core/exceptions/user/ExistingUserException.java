package com.ada.web.planner.core.exceptions.user;

import org.springframework.http.HttpStatus;

public class ExistingUserException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.CONFLICT;
    public ExistingUserException() {
        super("The user already exists.");
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
