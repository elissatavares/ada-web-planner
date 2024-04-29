package com.ada.web.planner.dto.task;

import com.ada.web.planner.core.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public record TaskDTO(Long id,
                      String title,
                      String description,
                      String created_at,
                      String due_date,
                      Boolean completed) {

    public static TaskDTO toDTO(Task task){
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), formatDateTime(task.getCreated_at()), formatDateTime(task.getDue_date()), task.getCompleted());
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        if(dateTime == null){
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, EEEE, HH:mm", java.util.Locale.getDefault());

        return dateTime.format(formatter);
    }
}
