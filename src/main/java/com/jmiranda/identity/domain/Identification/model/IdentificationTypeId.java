package com.jmiranda.identity.domain.Identification.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

import java.util.UUID;

public final class IdentificationTypeId {
    private final UUID value;

    private IdentificationTypeId(UUID value) {
        this.value = value;
    }

    public static IdentificationTypeId of(String raw) {
        if (raw == null || raw.isBlank()) {
            throw InvalidValueException.required("IdentificationTypeId");
        }

        try {
            return new IdentificationTypeId(UUID.fromString(raw));
        } catch (IllegalArgumentException e) {
            throw InvalidValueException.invalidFormat("IdentificationTypeId");
        }
    }

    public UUID value() {
        return value;
    }
}
