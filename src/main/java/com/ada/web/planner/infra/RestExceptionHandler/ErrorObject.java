package com.ada.web.planner.infra.RestExceptionHandler;

public record ErrorObject(String message, String field, Object parameter) {

}
