package com.jmiranda.identity.infrastructure.permission.web.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRolePermissionRepository extends JpaRepository<PermissionEntity, String> {
    boolean existsById(String roleId, String permissionId);

}
