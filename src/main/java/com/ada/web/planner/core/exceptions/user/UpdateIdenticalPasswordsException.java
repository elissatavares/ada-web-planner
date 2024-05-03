package com.ada.web.planner.core.exceptions.user;

import org.springframework.http.HttpStatus;

public class UpdateIdenticalPasswordsException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public UpdateIdenticalPasswordsException() {
        super("The password provided is the same as the current password");
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
