package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record FirstName(String value) {

    public FirstName {
        if (value == null) {
            throw new InvalidValueException("user.firstName.null");
        }

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidValueException("user.firstName.blank");
        }

        if (value.length() < 2 || value.length() > 64) {
            throw new InvalidValueException("user.firstName.size");
        }

        if (!value.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            throw new InvalidValueException("user.firstName.format");
        }
    }
}
