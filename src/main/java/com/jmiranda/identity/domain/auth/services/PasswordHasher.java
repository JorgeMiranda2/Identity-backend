package com.jmiranda.identity.domain.auth.services;

public interface PasswordHasher {
    String hash(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
