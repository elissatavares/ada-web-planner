package com.ada.web.planner.infra.RestExceptionHandler;

import com.ada.web.planner.core.exceptions.user.ExistingUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExistingUser.class)
    private ResponseEntity<RestErrorMessage> ExistingUserHandler(ExistingUser exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT,"Email unavailable");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

}
