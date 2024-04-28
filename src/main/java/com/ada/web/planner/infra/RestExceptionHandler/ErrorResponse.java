package com.ada.web.planner.infra.RestExceptionHandler;


public record ErrorResponse(
    String message, int code, String status, Object errors) {
}
