package com.ada.web.planner.core.exceptions.user;

public class ExistingUser extends RuntimeException{
    public ExistingUser() {
        super("The user already exists.");
    }

    public ExistingUser(String message) {
        super(message);
    }
}
