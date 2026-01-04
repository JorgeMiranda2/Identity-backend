package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null) {
            throw new InvalidValueException("user.phoneNumber.null");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.isBlank()) {
            throw new InvalidValueException("user.phoneNumber.blank");
        }

        if (!trimmedValue.matches("^\\+?[1-9]\\d{1,14}$")) {
            throw new InvalidValueException("user.phoneNumber.format");
        }

        value = trimmedValue;
    }
}
