package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SpringDataUserRoleRepository extends JpaRepository<UserRoleEntity, String> {

    Optional<UserRoleEntity> findByUserId(String userId);
}
