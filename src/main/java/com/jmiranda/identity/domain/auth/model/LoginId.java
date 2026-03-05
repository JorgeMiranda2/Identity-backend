package com.jmiranda.identity.domain.auth.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;
import com.jmiranda.identity.domain.user.model.UserId;

import java.util.Objects;
import java.util.UUID;

public final class LoginId {
    private final UUID value;
    private LoginId(UUID value){
        this.value = Objects.requireNonNull(value);
    }
    public static LoginId generate(){
        return new LoginId(UUID.randomUUID());
    }

    public static LoginId of(String value){
        if (value == null || value.isBlank()) {
            throw InvalidValueException.required("login.loginId.required");
        }

        try {
            return new LoginId(UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            throw InvalidValueException.invalidFormat("login.loginId.invalid");
        }
    }

    public UUID value(){
        return value;
    }
}
