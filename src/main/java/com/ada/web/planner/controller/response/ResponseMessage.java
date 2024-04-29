package com.ada.web.planner.controller.response;

import org.springframework.http.HttpStatus;

public enum ResponseMessage {
    USER_CREATED_SUCCESSFULLY("User created successfully", HttpStatus.CREATED),
    USER_DELETED_SUCCESSFULLY("User deleted successfully", HttpStatus.OK),
    USER_DETAILS_RETRIEVED_SUCCESSFULLY("User details retrieved successfully", HttpStatus.OK),
    USER_UPDATED_SUCCESSFULLY("Password has been updated successfully", HttpStatus.OK),

    TASK_CREATED_SUCCESSFULLY("Task created successfully", HttpStatus.CREATED),
    TASK_DELETED_SUCCESSFULLY("Task deleted successfully", HttpStatus.OK),
    TASK_DETAILS_RETRIEVED_SUCCESSFULLY("Task details retrieved successfully", HttpStatus.OK),
    TASK_UPDATED_SUCCESSFULLY("Task updated successfully", HttpStatus.OK);

    private final String message;
    private final HttpStatus httpStatus;

    ResponseMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
