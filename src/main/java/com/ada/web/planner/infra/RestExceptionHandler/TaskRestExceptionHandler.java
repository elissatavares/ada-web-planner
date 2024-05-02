package com.ada.web.planner.infra.RestExceptionHandler;

import com.ada.web.planner.core.exceptions.task.TaskAlreadyCompletedException;
import com.ada.web.planner.core.exceptions.task.TaskNotFoundException;
import com.ada.web.planner.core.exceptions.task.UserHasNoTasksException;
import com.ada.web.planner.config.response.ErrorObject;
import com.ada.web.planner.config.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskRestExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    private ResponseEntity<ErrorResponse> taskNotFoundHandler(TaskNotFoundException exception){
        ErrorObject errorObject = new ErrorObject("The task does not exist.",
                "task",
                "NOT_FOUND");

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                exception.httpStatus().value(),
                exception.httpStatus().getReasonPhrase(),
                errorObject);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserHasNoTasksException.class)
    private ResponseEntity<ErrorResponse> userHasNoTasksHandler(UserHasNoTasksException exception){
        ErrorObject errorObject = new ErrorObject("The user has no tasks.",
                "user",
                "NO_TASKS");

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                exception.httpStatus().value(),
                exception.httpStatus().getReasonPhrase(),
                errorObject);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskAlreadyCompletedException.class)
    private ResponseEntity<ErrorResponse> taskAlreadyCompletedHandler(TaskAlreadyCompletedException exception){
        ErrorObject errorObject = new ErrorObject("The task has already been completed.",
                "task",
                "ALREADY_COMPLETED");

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                exception.httpStatus().value(),
                exception.httpStatus().getReasonPhrase(),
                errorObject);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
