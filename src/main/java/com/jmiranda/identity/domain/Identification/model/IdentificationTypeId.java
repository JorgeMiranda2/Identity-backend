package com.jmiranda.identity.domain.Identification.model;

public final class IdentificationTypeId {
    private final String value;

    private IdentificationTypeId(String value) {
        this.value = value;
    }

    public static IdentificationTypeId of(String value) {
        return new IdentificationTypeId(value);
    }

    public String value() {
        return value;
    }
}
