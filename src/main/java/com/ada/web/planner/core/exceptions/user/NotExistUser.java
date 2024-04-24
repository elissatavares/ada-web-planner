package com.ada.web.planner.core.exceptions.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NotExistUser extends UsernameNotFoundException {
    public NotExistUser() {
        super("User does not exist");
    }
}
