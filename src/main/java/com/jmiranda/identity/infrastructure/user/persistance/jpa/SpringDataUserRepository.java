package com.jmiranda.identity.infrastructure.user.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByPersonalEmail(String email);

    boolean existsByPersonalEmail(String email);
}
