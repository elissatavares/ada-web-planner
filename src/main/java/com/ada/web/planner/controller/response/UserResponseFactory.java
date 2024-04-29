package com.ada.web.planner.controller.response;

import com.ada.web.planner.dto.response.ResponseDTO;
import com.ada.web.planner.dto.user.UserDTO;

public class UserResponseFactory {
    public static ResponseDTO createdSuccessfully(UserDTO userDTO) {
        return create(ResponseMessage.USER_CREATED_SUCCESSFULLY, userDTO);
    }

    public static ResponseDTO deletedSuccessfully(UserDTO userDTO) {
        return create(ResponseMessage.USER_DELETED_SUCCESSFULLY, userDTO);
    }

    public static ResponseDTO userDetails(UserDTO userDTO) {
        return create(ResponseMessage.USER_DETAILS_RETRIEVED_SUCCESSFULLY, userDTO);
    }

    public static ResponseDTO updatedSuccessfully() {
        return create(ResponseMessage.USER_UPDATED_SUCCESSFULLY, "protected data");
    }

    private static ResponseDTO create(ResponseMessage message, Object data) {
        return new ResponseDTO(message.getMessage(),
                message.getHttpStatus().value(),
                message.getHttpStatus().getReasonPhrase(),
                data);
    }
}
