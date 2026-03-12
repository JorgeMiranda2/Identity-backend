package com.jmiranda.identity.domain.auth.repository;

import com.jmiranda.identity.domain.auth.model.Login;

public interface LoginRepository {
    boolean existsByUsername(String username);
    Login findByUsername(String username);
}
