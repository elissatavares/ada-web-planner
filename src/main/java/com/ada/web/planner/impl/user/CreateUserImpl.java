package com.ada.web.planner.impl.user;

import com.ada.web.planner.core.exceptions.user.ExistingUser;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.core.usecases.user.CreateUser;
import com.ada.web.planner.infra.repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            user.setCreated_at(LocalDateTime.now());
            repository.save(user);
        }catch (RuntimeException exception){
            throw new ExistingUser();
        }
        return user;
    }
}
