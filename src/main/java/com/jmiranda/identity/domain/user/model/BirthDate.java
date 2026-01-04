package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record BirthDate(String value) {

    public BirthDate {
        if (value == null) {
            throw new InvalidValueException("BirthDate cannot be null");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.isBlank()) {
            throw new InvalidValueException("BirthDate cannot be blank");
        }

        // Simple regex to validate date format YYYY-MM-DD
        if (!trimmedValue.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidValueException("BirthDate must be in the format YYYY-MM-DD");
        }

        value = trimmedValue;
    }
}
