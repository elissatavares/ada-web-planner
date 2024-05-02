package com.ada.web.planner.infra.security;

import com.ada.web.planner.config.hateoas.HateoasUser;
import com.ada.web.planner.core.exceptions.user.PasswordIsInvalidException;
import com.ada.web.planner.core.exceptions.user.UserDoesNotExistException;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.dto.user.UserDTO;
import com.ada.web.planner.infra.repository.UserRepository;
import com.ada.web.planner.dto.user.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final SecurityConfig securityConfig;
    private final HateoasUser hateoasUser;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService, SecurityConfig securityConfig, HateoasUser hateoasUser) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.securityConfig = securityConfig;
        this.hateoasUser = hateoasUser;
    }

    public LoginResponse authorizeLogin(String login, String password){
        Optional<User> user = userRepository.findByLogin(login);
        if(user.isEmpty()){
            throw new UserDoesNotExistException();
        }

        boolean authorizedUser = securityConfig.passwordEncoder().matches(password, user.get().getPassword());

        if(!authorizedUser){
            throw new PasswordIsInvalidException();
        }

        user.get().add(hateoasUser.relUpdateLink(),
                hateoasUser.relSelfDeleteLink(),
                hateoasUser.relSelfReadLink());
        String token = jwtService.generateToken(user.get());
        return new LoginResponse(token, UserDTO.toDTO(user.get()));
    }


}
