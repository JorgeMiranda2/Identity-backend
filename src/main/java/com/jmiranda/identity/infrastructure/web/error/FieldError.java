package com.jmiranda.identity.infrastructure.web.error;

public record FieldError(
        String field,
        String message
) {}
