package com.ada.web.planner.service.user;

import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.DeleteUser;
import com.ada.web.planner.dto.user.UserDTO;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteUserImpl implements DeleteUser {

    private final UserRepository repository;

    public DeleteUserImpl(UserRepository plannerRepository) {
        this.repository = plannerRepository;
    }

    @Override
    public UserDTO delete(UUID idUser) {
        User user = repository.getById(idUser);
        repository.delete(user);
        return UserDTO.toDTO(user);
    }

}
