package com.jmiranda.identity.domain.Identification.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record IdentificationTypeName(String value) {
    public IdentificationTypeName {
        if (value == null) {
            throw InvalidValueException.required("IdentificationType.IdentificationTypeName.null");
        }
        value = value.trim();
        if (value.isBlank()) {
            throw InvalidValueException.required("IdentificationType.IdentificationTypeName.blank");
        }
    }
}
