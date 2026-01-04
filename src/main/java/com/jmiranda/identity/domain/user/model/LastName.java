package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record LastName(String value) {

    public LastName {
        if (value == null) {
            throw new InvalidValueException("LastName cannot be null");
        }

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidValueException("LastName cannot be blank");
        }

        if (value.length() < 2 || value.length() > 64) {
            throw new InvalidValueException("LastName must be between 2 and 64 characters");
        }

        if (!value.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            throw new InvalidValueException("LastName can only contain letters and spaces");
        }
    }
}
