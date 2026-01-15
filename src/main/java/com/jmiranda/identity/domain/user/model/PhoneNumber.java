package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null) {
            throw InvalidValueException.required("user.phoneNumber.null");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.isBlank()) {
            throw InvalidValueException.required("user.phoneNumber.blank");
        }

        if (!trimmedValue.matches("^\\+?[1-9]\\d{1,14}$")) {
            throw InvalidValueException.invalidFormat("user.phoneNumber.format");
        }

        value = trimmedValue;
    }
}
