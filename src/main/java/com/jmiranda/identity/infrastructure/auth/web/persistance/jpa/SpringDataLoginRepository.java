package com.jmiranda.identity.infrastructure.auth.web.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface SpringDataLoginRepository extends JpaRepository<LoginEntity, String> {
    Optional<Set<LoginEntity>> findByUser_id(String UserId);
    boolean existsByUsername(String username);
    Optional<LoginEntity> findByUsername(String username);
}
