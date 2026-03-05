package com.jmiranda.identity.domain.auth.policy;

public interface PasswordPolicy {
    boolean isValid(String password);
}
