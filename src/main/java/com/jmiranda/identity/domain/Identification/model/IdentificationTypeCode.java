package com.jmiranda.identity.domain.Identification.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record IdentificationTypeCode(String value) {
    public IdentificationTypeCode {
        if (value == null) {
            throw InvalidValueException.invalidFormat("IdentificationTypeCode");
        }
        value = value.trim();
        if (value.isBlank()) {
            throw InvalidValueException.invalidFormat("IdentificationTypeCode");

        }
    }
}
