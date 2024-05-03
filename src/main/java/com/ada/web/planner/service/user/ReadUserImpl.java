package com.ada.web.planner.service.user;

import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.dto.user.UserDTO;
import com.ada.web.planner.config.hateoas.HateoasUser;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReadUserImpl implements ReadUser {

    private final UserRepository repository;
    private final HateoasUser hateoasUser;

    public ReadUserImpl(UserRepository repository, HateoasUser hateoasUser) {
        this.repository = repository;
        this.hateoasUser = hateoasUser;
    }

    @Override
    public UserDTO read(UUID idUser) {
        User user = repository.getById(idUser);
        user.add(hateoasUser.relUpdateLink(), hateoasUser.relSelfDeleteLink());
        return UserDTO.toDTO(user);
    }
}

