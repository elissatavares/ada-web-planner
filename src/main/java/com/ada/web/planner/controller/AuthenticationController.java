package com.ada.web.planner.controller;

import com.ada.web.planner.core.dto.login.LoginResponseDTO;
import com.ada.web.planner.core.dto.login.LoginRequestDTO;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.dto.user.CreateUserRequestDTO;
import com.ada.web.planner.core.dto.user.CreateUserResponseDTO;
import com.ada.web.planner.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planner")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final CreateUser createService;

    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, CreateUser createUser, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.createService = createUser;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseDTO> registerUser(@RequestBody @Valid CreateUserRequestDTO userData) {
        User user = new User(userData);
        User userCreated = createService.create(user);
        return new ResponseEntity<>(CreateUserResponseDTO.toDTO(userCreated), HttpStatus.CREATED);
    }

    //    @PostMapping("logout")
//    public ResponseEntity<Object> logoutUser() {
//        // Implementation to logout the user
//        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//    }
}
