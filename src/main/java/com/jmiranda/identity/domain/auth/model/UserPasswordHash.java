package com.jmiranda.identity.domain.auth.model;

import com.jmiranda.identity.domain.auth.policy.PasswordPolicy;
import com.jmiranda.identity.domain.auth.services.PasswordHasher;

public final class UserPasswordHash {
    private final String value;

    private UserPasswordHash(String value) {
        this.value = value;
    }

    // Para cuando cargas desde la BD (ya viene hasheada)
    public static UserPasswordHash fromHash(String hash) {
        return new UserPasswordHash(hash);
    }

    // Para cuando creas una nueva (Validar pureza y complejidad)
    public static UserPasswordHash create(String rawPassword, PasswordPolicy policy, PasswordHasher hasher) {
        if (!policy.isValid(rawPassword)) {
            throw new IllegalArgumentException("password.policy.violation");
        }

        String hashedPassword = hasher.hash(rawPassword);
        return new UserPasswordHash(hashedPassword);
    }

    public String value() {
        return value;
    }

    public static UserPasswordHash of(String value) {
        return new UserPasswordHash(value);
    }

    // NO methods de decode ni verify aquí.
}
