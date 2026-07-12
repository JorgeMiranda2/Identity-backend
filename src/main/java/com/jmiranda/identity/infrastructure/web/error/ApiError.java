package com.jmiranda.identity.infrastructure.web.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ApiError(
        String code,
        String message,
        int status,
        String path,
        Instant timestamp,
        List<FieldError> fieldErrors
) {
    public ApiError(String code, String message, int status, String path, Instant timestamp) {
        this(code, message, status, path, timestamp, null);
    }
}
