package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record FirstName(String value) {

    public FirstName {
        if (value == null) {
            throw new InvalidValueException("FirstName cannot be null");
        }

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidValueException("FirstName cannot be blank");
        }

        if (value.length() < 2 || value.length() > 64) {
            throw new InvalidValueException("FirstName must be between 2 and 64 characters");
        }

        if (!value.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            throw new InvalidValueException("FirstName can only contain letters and spaces");
        }
    }
}
