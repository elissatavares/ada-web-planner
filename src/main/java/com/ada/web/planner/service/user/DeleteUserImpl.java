package com.ada.web.planner.service.user;

import com.ada.web.planner.core.exceptions.user.UserDoesNotExistException;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.DeleteUser;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUserImpl implements DeleteUser {

    private final UserRepository repository;

    @Autowired
    public DeleteUserImpl(UserRepository plannerRepository) {
        this.repository = plannerRepository;
    }

    @Override
    public User delete(String login) {
        User user = validateUserExist(login);
        repository.delete(user);
        return user;
    }

    private User validateUserExist(String login){
        Optional<User> user = repository.findByLogin(login);
        if(user.isEmpty()){
            throw new UserDoesNotExistException();
        }
        return user.get();
    }

}
