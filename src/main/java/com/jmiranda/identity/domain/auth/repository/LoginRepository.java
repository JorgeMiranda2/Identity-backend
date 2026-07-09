package com.jmiranda.identity.domain.auth.repository;

import com.jmiranda.identity.domain.auth.model.Login;

import java.util.Optional;

public interface LoginRepository {
    void save(Login login);
    boolean existsByUsername(String username);
    Optional<Login> findByUsername(String username);
}
