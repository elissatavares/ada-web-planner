package com.ada.web.planner.infra.RestExceptionHandler;

import com.ada.web.planner.core.exceptions.user.ExistingUserException;
import com.ada.web.planner.core.exceptions.user.PasswordIsInvalidException;
import com.ada.web.planner.core.exceptions.user.UpdateIdenticalPasswordsException;
import com.ada.web.planner.core.exceptions.user.UserDoesNotExistException;
import com.ada.web.planner.dto.response.ErrorObject;
import com.ada.web.planner.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

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

    @ExceptionHandler(PasswordIsInvalidException.class)
    private ResponseEntity<ErrorResponse> passwordIsInvalidHandler(PasswordIsInvalidException exception){
        ErrorObject errorObject = new ErrorObject("The password entered is incorrect",
                "password",
                "INVALID");

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                exception.httpStatus().value(),
                exception.httpStatus().getReasonPhrase(),
                errorObject);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
