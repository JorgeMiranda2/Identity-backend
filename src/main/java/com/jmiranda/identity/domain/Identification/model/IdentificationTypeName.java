package com.jmiranda.identity.domain.Identification.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record IdentificationTypeName(String value) {
    public IdentificationTypeName {
        if (value == null) {
            throw new InvalidValueException("IdentificationType.IdentificationTypeName.null");
        }
        value = value.trim();
        if (value.isBlank()) {
            throw new InvalidValueException("IdentificationType.IdentificationTypeName.blank");
        }
    }
}
