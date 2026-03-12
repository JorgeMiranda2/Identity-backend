package com.jmiranda.identity.infrastructure.auth.web.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLoginRepository extends JpaRepository<LoginEntity, String> {
    boolean existsByUsername(String username);
    LoginEntity findByUsername(String username);
}
