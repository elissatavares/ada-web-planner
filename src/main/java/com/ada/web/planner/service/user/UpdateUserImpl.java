package com.ada.web.planner.service.user;

import com.ada.web.planner.core.exceptions.user.UpdateIdenticalPasswordsException;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.UpdateUser;
import com.ada.web.planner.dto.user.UserDTO;
import com.ada.web.planner.config.hateoas.HateoasUser;
import com.ada.web.planner.infra.repository.UserRepository;
import com.ada.web.planner.infra.security.SecurityConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUserImpl implements UpdateUser {

    private final UserRepository repository;
    private final SecurityConfig securityConfig;
    private final HateoasUser hateoasUser;

    public UpdateUserImpl(UserRepository repository, SecurityConfig securityConfig, HateoasUser hateoasUser) {
        this.repository = repository;
        this.securityConfig = securityConfig;
        this.hateoasUser = hateoasUser;
    }

    @Override
    public UserDTO update(UUID idUser, String newPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = securityConfig.passwordEncoder();
        User user = repository.getById(idUser);

        if(bCryptPasswordEncoder.matches(newPassword, user.getPassword())){
            throw new UpdateIdenticalPasswordsException();
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.add(hateoasUser.relUpdateLink(), hateoasUser.relSelfDeleteLink(), hateoasUser.relSelfReadLink());
        repository.saveAndFlush(user);
        return UserDTO.toDTO(user);
    }

}
