package com.ada.web.planner.core.usecases.user;

import com.ada.web.planner.core.model.User;

public interface UpdateUser {

    void update(String login, String newPassword);
}
