package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null) {
            throw new InvalidValueException("PhoneNumber cannot be null");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.isBlank()) {
            throw new InvalidValueException("PhoneNumber cannot be blank");
        }

        if (!trimmedValue.matches("^\\+?[1-9]\\d{1,14}$")) {
            throw new InvalidValueException("PhoneNumber must be in valid E.164 format");
        }

        value = trimmedValue;
    }
}
