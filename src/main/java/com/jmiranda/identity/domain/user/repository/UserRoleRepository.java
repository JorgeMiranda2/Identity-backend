package com.jmiranda.identity.domain.user.repository;

import com.jmiranda.identity.domain.auth.model.LoginId;
import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.model.RoleId;
import com.jmiranda.identity.domain.user.model.HumanUser;
import com.jmiranda.identity.domain.user.model.UserId;

import java.util.Optional;
import java.util.Set;

public interface UserRoleRepository {
    void assign(LoginId loginId, RoleId roleId);
    Optional<HumanUser> findByUserId(UserId id);
    Optional<Set<Role>> findRolesByUserId(UserId id);
}
