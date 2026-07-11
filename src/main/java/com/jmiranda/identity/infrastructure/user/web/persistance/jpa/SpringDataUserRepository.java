package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, String > {
    Optional<UserEntity> findByPersonalEmail(String email);

    boolean existsByPersonalEmail(String email);
    Optional<UserEntity> findByIdentificationNumber(String identificationNumber);

    Optional<UserEntity> findById(String id);

    Optional<LoginEntity> findFirstById(String userId);

    Optional<Set<LoginEntity>> findLoginsById(String userId);
}
