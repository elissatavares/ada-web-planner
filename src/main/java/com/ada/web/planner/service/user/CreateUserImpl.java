package com.ada.web.planner.service.user;

import com.ada.web.planner.core.exceptions.user.ExistingUserException;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserImpl implements CreateUser {

    private final UserRepository repository;

    @Autowired
    public CreateUserImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        validateUserExist(user.getLogin());
        repository.save(user);
        return user;
    }
    private void validateUserExist(String login){
        if(repository.findByLogin(login).isPresent()){
            throw new ExistingUserException();
        }
    }
}
