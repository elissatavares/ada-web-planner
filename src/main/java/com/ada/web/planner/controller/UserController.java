package com.ada.web.planner.controller;

import com.ada.web.planner.core.dto.user.CreateUserRequestDTO;
import com.ada.web.planner.core.dto.user.CreateUserResponseDTO;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.core.usecases.user.DeleteUser;
import com.ada.web.planner.core.usecases.user.ReadUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("planner/user")
public class UserController {
    private final CreateUser createService;
    private final DeleteUser deleteService;
    private final ReadUser getService;

    @Autowired
    public UserController(CreateUser createUser, DeleteUser deleteUser, ReadUser getUser) {
        this.createService = createUser;
        this.deleteService = deleteUser;
        this.getService = getUser;
    }

    @GetMapping
    public ResponseEntity<String> applicationDetails(){
        return ResponseEntity.accepted().body("Hello");
    }


//
//    @GetMapping
//    public ResponseEntity<CreateUserResponseDTO> getUserInfo() {
//        // Implementation to get user information
//        return new ResponseEntity<>(new CreateUserResponseDTO(), HttpStatus.ACCEPTED);
//    }
//
//    @PutMapping
//    public ResponseEntity<CreateUserResponseDTO> resetUserPassword() {
//        // Implementation to reset user password
//        return new ResponseEntity<>(new CreateUserResponseDTO(), HttpStatus.ACCEPTED);
//    }
    //@DeleteMapping
//    public ResponseEntity<CreateTaskResponseDTO> deleteTodo() {
//        // Implementation to delete a specific todo by ID
//        return new ResponseEntity<>(new CreateTaskResponseDTO(), HttpStatus.OK);
//    }
}

