package com.jmiranda.identity.infrastructure.role.web.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataRoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByCode(String code);

     boolean existsByCode(String code);
}
