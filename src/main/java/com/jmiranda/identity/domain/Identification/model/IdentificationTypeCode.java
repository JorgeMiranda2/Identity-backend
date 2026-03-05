package com.jmiranda.identity.domain.Identification.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public class IdentificationTypeCode{

    private String value;
    private IdentificationTypeCode(String value) {
        if (value == null) {
            throw InvalidValueException.invalidFormat("IdentificationTypeCode");
        }
        value = value.trim();
        if (value.isBlank()) {
            throw InvalidValueException.invalidFormat("IdentificationTypeCode");

        }
        this.value = value;
    }

    public static IdentificationTypeCode of(String value) {
        return new IdentificationTypeCode(value);
    }

    public String value() {
        return value;
    }
}
