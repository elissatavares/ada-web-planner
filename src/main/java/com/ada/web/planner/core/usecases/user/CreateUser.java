package com.ada.web.planner.core.usecases.user;

import com.ada.web.planner.core.model.User;
import com.ada.web.planner.dto.user.UserDTO;

public interface CreateUser {
    UserDTO create(User user);
}
