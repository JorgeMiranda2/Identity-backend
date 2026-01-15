package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.Identification.model.IdentificationTypeId;
import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

import java.util.Objects;
import java.util.UUID;

public final class UserId {

    private final UUID value;

    private UserId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId system() {
        return new UserId(
                UUID.fromString("ffffffff-ffff-ffff-ffff-ffffffffffff")
        );
    }

    public static UserId anonymous() {
        return new UserId(
                UUID.fromString("00000000-0000-0000-0000-000000000001")
        );
    }

    public static UserId of(String value) {
        if (value == null || value.isBlank()) {
            throw InvalidValueException.required("user.userId.required");
        }

        try {
            return new UserId(UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            throw InvalidValueException.invalidFormat("user.userId.invalid");
        }
    }

    public UUID value() {
        return value;
    }
}

