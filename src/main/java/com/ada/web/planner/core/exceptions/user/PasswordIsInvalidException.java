package com.ada.web.planner.core.exceptions.user;

import org.springframework.http.HttpStatus;

public class PasswordIsInvalidException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public PasswordIsInvalidException() {
        super("Incorrect password");
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
