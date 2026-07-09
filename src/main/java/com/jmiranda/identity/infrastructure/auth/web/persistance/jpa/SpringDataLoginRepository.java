package com.jmiranda.identity.infrastructure.auth.web.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataLoginRepository extends JpaRepository<LoginEntity, String> {
    boolean existsByUsername(String username);
    Optional<LoginEntity> findByUsername(String username);
}
