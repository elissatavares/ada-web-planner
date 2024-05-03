package com.ada.web.planner.service.user;

import com.ada.web.planner.dto.user.UserDTO;
import com.ada.web.planner.config.hateoas.HateoasUser;
import com.ada.web.planner.infra.security.SecurityConfig;
import com.ada.web.planner.core.exceptions.user.ExistingUserException;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class CreateUserImpl implements CreateUser {

    private final UserRepository repository;
    private final SecurityConfig securityConfig;
    private final HateoasUser hateoasUser;

    public CreateUserImpl(UserRepository repository, SecurityConfig securityConfig, HateoasUser hateoasUser) {
        this.repository = repository;
        this.securityConfig = securityConfig;
        this.hateoasUser = hateoasUser;
    }

    @Override
    public UserDTO create(User user) {
        if (repository.existsByLogin(user.getLogin())) {
            throw new ExistingUserException();
        }
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        repository.save(user);

        user.add(hateoasUser.relSelfLoginLink());
        return UserDTO.toDTO(user);
    }
}
