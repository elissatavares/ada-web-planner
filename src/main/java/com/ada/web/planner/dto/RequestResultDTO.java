package com.ada.web.planner.dto;

import com.ada.web.planner.dto.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;

public record RequestResultDTO(
        @NotBlank
        String message,
        @NotBlank
        int code,
        @NotBlank
        String status,
        @NotBlank
        Object object) {

    public static RequestResultDTO successfullyCreated(UserDTO userDTO) {
        return new RequestResultDTO("User created successfully", HttpStatus.CREATED.value() , HttpStatus.CREATED.getReasonPhrase(), userDTO);
    }

    public static RequestResultDTO successfullyDeleted(UserDTO userDTO) {
        return new RequestResultDTO("User deleted successfully", HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), userDTO);
    }

    public static RequestResultDTO userDetails(UserDTO userDTO) {
        return new RequestResultDTO("User details retrieved successfully", HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), userDTO);
    }
    public static RequestResultDTO successfullyUpdate() {
        return new RequestResultDTO("Password has been updated successfully", HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "protected data");
    }
}

