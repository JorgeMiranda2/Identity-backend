package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record LastName(String value) {

    public LastName {
        if (value == null) {
            throw new InvalidValueException("user.lastName.null");
        }

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidValueException("user.lastName.blank");
        }

        if (value.length() < 2 || value.length() > 64) {
            throw new InvalidValueException("user.lastName.size");
        }

        if (!value.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            throw new InvalidValueException("user.lastName.format");
        }
    }
}
