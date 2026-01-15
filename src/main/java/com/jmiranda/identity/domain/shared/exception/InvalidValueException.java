package com.jmiranda.identity.domain.shared.exception;

public class InvalidValueException extends DomainException {

    private InvalidValueException(String field, String code) {
        super(code, field);
    }

    public static InvalidValueException invalidFormat(String field) {
        return new InvalidValueException(field, "INVALID_FORMAT");
    }

    public static InvalidValueException required(String field) {
        return new InvalidValueException(field, "REQUIRED");
    }
}
