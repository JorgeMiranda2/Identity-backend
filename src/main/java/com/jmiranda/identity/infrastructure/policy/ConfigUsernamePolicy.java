package com.jmiranda.identity.infrastructure.policy;

import com.jmiranda.identity.domain.auth.policy.UsernamePolicy;

public class ConfigUsernamePolicy implements UsernamePolicy {
    @Override
    public boolean isValid(String username) {
        // Configurate Username Policy: Alphanumeric characters, length between 3 and 20
        String usernamePattern = "^[a-zA-Z0-9]{3,20}$";
        return username != null && username.matches(usernamePattern);
    }
}
