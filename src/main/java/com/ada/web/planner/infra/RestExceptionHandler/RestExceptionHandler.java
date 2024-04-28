package com.ada.web.planner.infra.RestExceptionHandler;

import com.ada.web.planner.core.exceptions.user.ExistingUserException;
import com.ada.web.planner.core.exceptions.user.UpdateIdenticalPasswordsException;
import com.ada.web.planner.core.exceptions.user.UserDoesNotExistException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExistingUserException.class)
    private ResponseEntity<ErrorResponse> existingUserHandler(ExistingUserException exception){
        ErrorObject errorObject = new ErrorObject("Login is not available",
                "login",
                "UNAVAILABLE");

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                exception.httpStatus().value(),
                exception.httpStatus().getReasonPhrase(),
                errorObject);

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    private ResponseEntity<ErrorResponse> notExistUserHandler(UserDoesNotExistException exception){
        ErrorObject errorObject = new ErrorObject("Unable to access this login",
                "login",
                "NOT FOUND");

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                exception.httpStatus().value(),
                exception.httpStatus().getReasonPhrase(),
                errorObject);

        return new ResponseEntity<>(errorResponse, HttpStatus.FOUND);
    }

    @ExceptionHandler(UpdateIdenticalPasswordsException.class)
    private ResponseEntity<ErrorResponse> identicalPasswordHandler(UpdateIdenticalPasswordsException exception){
        ErrorObject errorObject = new ErrorObject("The password provided must be different from the current password",
                "password",
                "IDENTICAL");

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                exception.httpStatus().value(),
                exception.httpStatus().getReasonPhrase(),
                errorObject);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorObject> errors = getErrors(exception);
        ErrorResponse errorResponse = getErrorResponse(status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(HttpStatusCode status, List<ErrorObject> errors) {
        return new ErrorResponse("Request has invalid fields", status.value(),
                status.toString(), errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }

}
