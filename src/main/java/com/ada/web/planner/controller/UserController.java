package com.ada.web.planner.controller;

import com.ada.web.planner.controller.response.UserResponseFactory;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.core.usecases.user.DeleteUser;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.core.usecases.user.UpdateUser;
import com.ada.web.planner.dto.user.CreateUserRequestDTO;
import com.ada.web.planner.dto.response.ResponseDTO;
import com.ada.web.planner.dto.user.UpdateUserRequestDTO;
import com.ada.web.planner.dto.user.UserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("planner/user")
public class UserController {
    private final CreateUser createService;

    private final ReadUser readService;

    private final UpdateUser updateService;

    private final DeleteUser deleteService;

    @Autowired
    public UserController(CreateUser createService, ReadUser readService, UpdateUser updateService, DeleteUser deleteService) {
        this.createService = createService;
        this.readService = readService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/{login}")
    public ResponseEntity<ResponseDTO> readUser(@PathVariable("login") @NotBlank String login) {
        User user = readService.read(login);
        UserDTO userDTO = UserDTO.toDTO(user);
        ResponseDTO requestResultDTO = UserResponseFactory.userDetails(userDTO);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid CreateUserRequestDTO userData) {
        User user = new User(userData);
        User userCreated = createService.create(user);
        UserDTO userDTO = UserDTO.toDTO(userCreated);
        ResponseDTO requestResultDTO = UserResponseFactory.createdSuccessfully(userDTO);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{login}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable("login") @NotBlank String login,
                                                  @RequestBody @Valid UpdateUserRequestDTO newPassword) {

        updateService.update(login, newPassword.password());
        ResponseDTO requestResultDTO = UserResponseFactory.updatedSuccessfully();
        return new ResponseEntity<>(requestResultDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{login}")
    public ResponseEntity<ResponseDTO> deleteUSer(@PathVariable("login") @NotBlank String login) {
        User user = deleteService.delete(login);
        UserDTO userDTO = UserDTO.toDTO(user);
        ResponseDTO requestResultDTO = UserResponseFactory.deletedSuccessfully(userDTO);
        return new ResponseEntity<>(requestResultDTO, HttpStatus.OK);
    }
}

