package com.ada.web.planner.controller;

import com.ada.web.planner.dto.response.UserResponseFactory;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.dto.response.ResponseDTO;
import com.ada.web.planner.dto.user.CreateUserRequestDTO;
import com.ada.web.planner.dto.user.LoginRequestDto;
import com.ada.web.planner.dto.user.LoginResponse;
import com.ada.web.planner.dto.user.UserDTO;
import com.ada.web.planner.infra.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("planner")
public class PlannerController {

    private final AuthenticationService authorizationService;

    private final CreateUser createService;

    public PlannerController(AuthenticationService authorizationService, CreateUser createService) {
        this.authorizationService = authorizationService;
        this.createService = createService;
    }

    @Operation(description = "Realiza login")
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> authorizeLogin(@RequestBody @Valid LoginRequestDto loginData) {
        LoginResponse loginResponse = authorizationService.authorizeLogin(loginData.login(), loginData.password());
        ResponseDTO requestResultDTO = UserResponseFactory.successfullyLoggedIn(loginResponse);
        return ResponseEntity.ok().body(requestResultDTO);
    }

    @Operation(description = "Resgistra um usuário")
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid CreateUserRequestDTO userData) {
        User user = new User(userData);
        UserDTO userDTO = createService.create(user);
        ResponseDTO requestResultDTO = UserResponseFactory.createdSuccessfully(userDTO);
        return ResponseEntity.created(linkTo(methodOn(UserController.class).readUser())
                .withRel("self")
                .withType(RequestMethod.GET.name()).toUri()).body(requestResultDTO);
    }
}
