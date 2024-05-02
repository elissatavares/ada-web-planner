package com.ada.web.planner.core.usecases.user;

import com.ada.web.planner.dto.user.UserDTO;

import java.util.UUID;

public interface UpdateUser {

    UserDTO update(UUID idUser, String newPassword);
}
