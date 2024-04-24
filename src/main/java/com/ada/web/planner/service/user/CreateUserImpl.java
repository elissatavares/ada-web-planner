package com.ada.web.planner.service.user;

import com.ada.web.planner.core.exceptions.user.ExistingUser;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.infra.repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateUserImpl implements CreateUser {

    private final PlannerRepository repository;

    @Autowired
    public CreateUserImpl(PlannerRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        validateUserExist(user.getLogin());
        encryptPassword(user);
        user.setCreated_at(LocalDateTime.now());
        repository.save(user);
        return user;
    }
    @Override
    public void validateUserExist(String login){
        if(repository.findByLogin(login) != null){
            throw new ExistingUser();
        }
    }

    private void encryptPassword(User user){
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
    }
}
