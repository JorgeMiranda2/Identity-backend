package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.domain.auth.model.AssignmentSource;
import com.jmiranda.identity.domain.auth.model.LoginId;

import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.model.RoleId;
import com.jmiranda.identity.domain.user.model.*;
import com.jmiranda.identity.domain.user.repository.UserRoleRepository;
import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.LoginEntity;
import com.jmiranda.identity.infrastructure.role.web.persistance.jpa.RoleEntity;
import com.jmiranda.identity.infrastructure.user.web.persistance.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class JpaUserRoleRepository implements UserRoleRepository {

    private final SpringDataLoginRoleRepository springDataRepository;
    private final UserMapper mapper;
    private final Clock systemClock;

    @PersistenceContext
    private EntityManager entityManager;

    public JpaUserRoleRepository(
            SpringDataLoginRoleRepository springDataRepository,
            UserMapper mapper,
            Clock clock
    ) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
        this.systemClock = clock;
    }

    @Override
    public void assign(LoginId loginId, RoleId roleId) {
        RoleEntity roleRef = entityManager.getReference(RoleEntity.class, roleId.value().toString());
            LoginEntity loginRef = entityManager.getReference(LoginEntity.class, loginId.value().toString());

        LoginRoleEntity entity = new LoginRoleEntity(
                UUID.randomUUID().toString(),
                loginRef,
                roleRef,
                AssignmentSource.SELF_REGISTER,
                systemClock.instant(),
                null
        );
        springDataRepository.save(entity);
    }

    @Override
    public Optional<HumanUser> findByUserId(UserId id) {
        return Optional.empty();
    }

    @Override
    public Optional<Set<Role>> findRolesByUserId(UserId id) {
        return Optional.empty();
    }
}
