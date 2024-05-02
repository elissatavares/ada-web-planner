package com.ada.web.planner.core.usecases.user;

import com.ada.web.planner.core.model.User;
import com.ada.web.planner.dto.user.UserDTO;

import java.util.UUID;

public interface DeleteUser {

    UserDTO delete(UUID idUser);
}
