package com.jmiranda.identity.domain.Identification.model;

public final class IdentificationCode {
    private final String value;
    private IdentificationCode(String value) {
        this.value = value;
    }
    public static IdentificationCode of(String value) {
        return new IdentificationCode(value);
    }

    public String value() {
        return value;
    }
}
