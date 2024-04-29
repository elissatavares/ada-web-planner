package com.ada.web.planner.service.user;

import com.ada.web.planner.core.exceptions.user.UserDoesNotExistException;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.ReadUser;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadUserImpl implements ReadUser {

    private final UserRepository repository;

    @Autowired
    public ReadUserImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User read(String login) {
        return validateUserExist(login);
    }
    private User validateUserExist(String login) {
        Optional<User> user = repository.findByLogin(login);
        if (user.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        return user.get();
    }
}

