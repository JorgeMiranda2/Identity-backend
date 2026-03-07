package com.jmiranda.identity.domain.role.repository;

import com.jmiranda.identity.domain.role.model.Code;
import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.model.RoleId;


import java.util.Optional;

public interface RoleRepository {
    void save(Role role);
    Optional<Role> findById(RoleId id);

    Optional<Role> findByCode(
            Code code
    );
}
