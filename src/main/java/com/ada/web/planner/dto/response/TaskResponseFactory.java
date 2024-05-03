package com.ada.web.planner.dto.response;

import com.ada.web.planner.dto.task.TaskDTO;

public class TaskResponseFactory {
    public static ResponseDTO createdSuccessfully(TaskDTO taskDTO) {
        return create(ResponseMessage.TASK_CREATED_SUCCESSFULLY, taskDTO);
    }

    public static ResponseDTO deletedSuccessfully(TaskDTO taskDTO) {
        return create(ResponseMessage.TASK_DELETED_SUCCESSFULLY, taskDTO);
    }

    public static ResponseDTO taskDetails(Object taskDTO) {
        return create(ResponseMessage.TASK_DETAILS_RETRIEVED_SUCCESSFULLY, taskDTO);
    }

    public static ResponseDTO updatedSuccessfully(TaskDTO taskDTO) {
        return create(ResponseMessage.TASK_UPDATED_SUCCESSFULLY, taskDTO);
    }

    private static ResponseDTO create(ResponseMessage message, Object data) {
        return new ResponseDTO(message.getMessage(),
                message.getHttpStatus().value(),
                message.getHttpStatus().getReasonPhrase(),
                data);
    }
}
