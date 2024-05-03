package com.ada.web.planner.dto.response;

import com.ada.web.planner.dto.user.LoginResponse;
import com.ada.web.planner.dto.user.UserDTO;

public class UserResponseFactory {
    public static ResponseDTO createdSuccessfully(UserDTO userDTO) {
        return create(ResponseMessage.USER_CREATED_SUCCESSFULLY, userDTO);
    }
    public static ResponseDTO successfullyLoggedIn(LoginResponse loginResponse) {
        return create(ResponseMessage.USER_SUCCESSFULLY_LOGGED_IN, loginResponse);
    }

    public static ResponseDTO deletedSuccessfully(UserDTO userDTO) {
        return create(ResponseMessage.USER_DELETED_SUCCESSFULLY, userDTO);
    }

    public static ResponseDTO userDetails(UserDTO userDTO) {
        return create(ResponseMessage.USER_DETAILS_RETRIEVED_SUCCESSFULLY, userDTO);
    }

    public static ResponseDTO updatedSuccessfully(UserDTO userDTO) {
        return create(ResponseMessage.USER_UPDATED_SUCCESSFULLY, userDTO);
    }

    private static ResponseDTO create(ResponseMessage message, Object data) {
        return new ResponseDTO(message.getMessage(),
                message.getHttpStatus().value(),
                message.getHttpStatus().getReasonPhrase(),
                data);
    }
}
