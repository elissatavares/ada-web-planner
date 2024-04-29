package com.ada.web.planner.service.user;

import com.ada.web.planner.core.exceptions.user.UpdateIdenticalPasswordsException;
import com.ada.web.planner.core.exceptions.user.UserDoesNotExistException;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.UpdateUser;
import com.ada.web.planner.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserImpl implements UpdateUser {

    private final UserRepository repository;

    @Autowired
    public UpdateUserImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void update(String login, String newPassword) {
        User user = validateUserExist(login);
        compareOldPasswordWithNewPassword(user.getPassword(), newPassword);
        user.setPassword(newPassword);
        User updatedPassword = repository.saveAndFlush(user);
    }
    private User validateUserExist(String login) {
        Optional<User> user = repository.findByLogin(login);
        if (user.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        return user.get();
    }

    private void compareOldPasswordWithNewPassword(String oldPassword, String newPassword){
        if(oldPassword.equalsIgnoreCase(newPassword)){
            throw new UpdateIdenticalPasswordsException();
        }
    }
}
