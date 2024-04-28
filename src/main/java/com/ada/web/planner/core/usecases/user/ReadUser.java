package com.ada.web.planner.core.usecases.user;

import com.ada.web.planner.core.model.User;

public interface ReadUser {
    User read(String login);
}
