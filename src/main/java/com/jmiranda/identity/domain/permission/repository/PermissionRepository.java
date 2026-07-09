package com.jmiranda.identity.domain.permission.repository;

public interface PermissionRepository {
     boolean existsByAuthority(String authority);
}
