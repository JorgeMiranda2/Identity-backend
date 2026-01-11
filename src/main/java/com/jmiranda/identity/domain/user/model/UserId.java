package com.jmiranda.identity.domain.user.model;

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

    public static UserId from(UUID value) {
        return new UserId(value);
    }

    public UUID value() {
        return value;
    }
}

