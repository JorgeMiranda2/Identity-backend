package com.jmiranda.identity.infrastructure.policy;

import com.jmiranda.identity.domain.auth.policy.PasswordPolicy;

public class ConfigPasswordPolicy implements PasswordPolicy {
    @Override
    public boolean isValid(String password) {
        // Configurate Password Policy: Length >= 8, at least one uppercase, one lowercase, one digit and one special character
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordPattern);
    }
}
