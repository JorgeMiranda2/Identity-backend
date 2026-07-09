package com.jmiranda.identity.infrastructure.permission.web.persistance.jpa;

import com.jmiranda.identity.domain.role.model.RoleId;
import com.jmiranda.identity.domain.role.repository.RolePermissionRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class JpaRolePermissionRepository implements RolePermissionRepository {

    @Override
    public Set<String> findAuthoritiesByRoleIds(Set<RoleId> roleIds) {
        return Set.of();
    }
}
