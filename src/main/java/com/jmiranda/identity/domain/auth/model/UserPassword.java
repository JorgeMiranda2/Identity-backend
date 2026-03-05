package com.jmiranda.identity.domain.auth.model;

import com.jmiranda.identity.domain.auth.policy.PasswordPolicy;
import com.jmiranda.identity.domain.auth.services.PasswordHasher;

public final class UserPassword {
    private final String value;

    private UserPassword(String value) {
        this.value = value;
    }

    // Para cuando cargas desde la BD (ya viene hasheada)
    public static UserPassword fromHash(String hash) {
        return new UserPassword(hash);
    }

    // Para cuando creas una nueva (Validar pureza y complejidad)
    public static UserPassword create(String rawPassword, PasswordPolicy policy, PasswordHasher hasher) {
        if (!policy.isValid(rawPassword)) {
            throw new IllegalArgumentException("password.policy.violation");
        }

        String hashedPassword = hasher.hash(rawPassword);
        return new UserPassword(hashedPassword);
    }

    public String value() {
        return value;
    }

    // NO methods de decode ni verify aquí.
}
