package com.ada.web.planner.controller;

import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.core.usecases.user.DeleteUser;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.controller.dto.user.UserRequestDTO;
import com.ada.web.planner.controller.dto.user.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("planner")
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

    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO userData) {
        User user = new User(userData);
        User userCreated = createService.create(user);
        return new ResponseEntity<>(UserResponseDTO.toDTO(userCreated), HttpStatus.CREATED);
    }

//    @PostMapping("login")
//    public ResponseEntity<Object> loginUser() {
//        // Implementation to login the user
//        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//    }
//
//    @PostMapping("logout")
//    public ResponseEntity<Object> logoutUser() {
//        // Implementation to logout the user
//        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//    }
//
//    @GetMapping("user")
//    public ResponseEntity<UserResponseDTO> getUserInfo() {
//        // Implementation to get user information
//        return new ResponseEntity<>(new UserResponseDTO(), HttpStatus.ACCEPTED);
//    }
//
//    @PostMapping("user/reset")
//    public ResponseEntity<UserResponseDTO> resetUserPassword() {
//        // Implementation to reset user password
//        return new ResponseEntity<>(new UserResponseDTO(), HttpStatus.ACCEPTED);
//    }
}

