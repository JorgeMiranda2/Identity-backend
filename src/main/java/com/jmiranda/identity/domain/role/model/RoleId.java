package com.jmiranda.identity.domain.role.model;

import java.util.UUID;

public final class RoleId {
    private final UUID value;

    private RoleId(UUID value) {
        this.value = value;
    }

    public static RoleId generate() {
        return new RoleId(UUID.randomUUID());
    }

    public static RoleId of(String value) {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("role.roleId.required");
        }

        try {
            return new RoleId(UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("role.roleId.invalid");
        }
    }

    public UUID value() {
        return value;
    }
}
