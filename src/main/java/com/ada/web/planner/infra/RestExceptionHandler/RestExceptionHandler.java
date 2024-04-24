package com.ada.web.planner.infra.RestExceptionHandler;

import com.ada.web.planner.core.exceptions.user.ExistingUser;
import com.ada.web.planner.core.exceptions.user.NotExistUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExistingUser.class)
    private ResponseEntity<RestErrorMessage> existingUserHandler(ExistingUser exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(NotExistUser.class)
    private ResponseEntity<RestErrorMessage> notExistUser(NotExistUser loginNotFoundException){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.FOUND, loginNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.FOUND).body(errorMessage);
    }

}
