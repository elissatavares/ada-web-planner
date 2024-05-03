package com.ada.web.planner.controller;

import com.ada.web.planner.dto.response.UserResponseFactory;
import com.ada.web.planner.core.usecases.user.DeleteUser;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.core.usecases.user.UpdateUser;
import com.ada.web.planner.dto.user.*;
import com.ada.web.planner.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("planner")
public class UserController {

    private final ReadUser readService;

    private final UpdateUser updateService;

    private final DeleteUser deleteService;

    public UserController(ReadUser readService, UpdateUser updateService, DeleteUser deleteService) {
        this.readService = readService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @Operation(description = "Exibe os detalhes do usuário")
    @GetMapping
    public ResponseEntity<ResponseDTO> readUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = readService.read(UUID.fromString(authentication.getName()));
        ResponseDTO requestResultDTO = UserResponseFactory.userDetails(userDTO);
        return ResponseEntity.ok().body(requestResultDTO);
    }


    @Operation(description = "Atualiza a senha de um usuário")
    @PutMapping
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody @Valid UpdateUserRequestDTO newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = updateService.update(UUID.fromString(authentication.getName()), newPassword.password());
        ResponseDTO requestResultDTO = UserResponseFactory.updatedSuccessfully(userDTO);
        return ResponseEntity.ok().body(requestResultDTO);
    }

    @Operation(description = "Deleta um usuário")
    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = deleteService.delete(UUID.fromString(authentication.getName()));
        ResponseDTO requestResultDTO = UserResponseFactory.deletedSuccessfully(userDTO);
        return ResponseEntity.ok().body(requestResultDTO);
    }
}

