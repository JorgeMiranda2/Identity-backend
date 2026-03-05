package com.jmiranda.identity.domain.auth.model;

import com.jmiranda.identity.domain.auth.policy.UsernamePolicy;
import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public final class Username {
    private final String value;

    private Username(String value) {
        this.value = value;
    }

    public static Username create(String value, UsernamePolicy policy) {
        if (value == null || !policy.isValid(value)) {
            throw InvalidValueException.invalidFormat(value);
        }
        return new Username(value);
    }

    public String value() {
        return value;
    }
}
