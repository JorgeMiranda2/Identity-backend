package com.jmiranda.identity.domain.role.repository;

import com.jmiranda.identity.domain.role.model.RoleId;

import java.util.Set;

public interface RolePermissionRepository {
    Set<String> findAuthoritiesByRoleIds(Set<RoleId> roleIds);
}
