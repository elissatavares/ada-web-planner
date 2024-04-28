package com.ada.web.planner.core.exceptions.user;

import org.springframework.http.HttpStatus;

public class UserDoesNotExistException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.FOUND;
    public UserDoesNotExistException() {
        super("The user does not exist.");
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
