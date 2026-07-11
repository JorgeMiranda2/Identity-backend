package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SpringDataLoginRoleRepository extends JpaRepository<LoginRoleEntity, String> {

    List<LoginRoleEntity> findByLogin_Id(String loginId);
    List<LoginRoleEntity> findByLogin_IdIn(Collection<String> loginIds);
}
