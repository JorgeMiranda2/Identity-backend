package com.jmiranda.identity.domain.Identification.model;

import java.util.UUID;

public final class IdentificationTypeId {
    private final UUID value;

    private IdentificationTypeId(UUID value) {
        this.value = value;
    }

    public static IdentificationTypeId of(UUID value) {
        return new IdentificationTypeId(value);
    }

    public UUID value() {
        return value;
    }
}
