package com.ada.web.planner.service.authentication;

import com.ada.web.planner.core.exceptions.user.NotExistUser;
import com.ada.web.planner.infra.repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final PlannerRepository repository;

    @Autowired
    public AuthorizationService(PlannerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        try {
            return repository.findByLogin(login);
        } catch (UsernameNotFoundException loginNotFoundException){
            System.out.println(loginNotFoundException.getMessage());
            System.out.println(loginNotFoundException.getCause().toString());
            System.out.println(loginNotFoundException.toString());
            throw new NotExistUser();
        }
    }
}
