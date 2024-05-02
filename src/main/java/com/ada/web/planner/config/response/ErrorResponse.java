package com.ada.web.planner.config.response;


public record ErrorResponse(
    String message, int code, String status, Object errors) {
}
