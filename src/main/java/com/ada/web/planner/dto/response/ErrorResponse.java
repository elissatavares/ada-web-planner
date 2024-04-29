package com.ada.web.planner.dto.response;


public record ErrorResponse(
    String message, int code, String status, Object errors) {
}
