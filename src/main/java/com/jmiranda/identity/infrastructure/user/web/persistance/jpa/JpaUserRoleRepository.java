package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.model.RoleId;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.HumanUser;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.domain.user.repository.UserRepository;
import com.jmiranda.identity.domain.user.repository.UserRoleRepository;
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

    private final SpringDataUserRoleRepository springDataRepository;
    private final UserMapper mapper;
    private final Clock systemClock;

    @PersistenceContext
    private EntityManager entityManager;

    public JpaUserRoleRepository(
            SpringDataUserRoleRepository springDataRepository,
            UserMapper mapper,
            Clock clock
    ) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
        this.systemClock = clock;
    }

    @Override
    public void assign(UserId userId, RoleId roleId) {
        RoleEntity roleRef = entityManager.getReference(RoleEntity.class, roleId.value().toString());

        UserRoleEntity entity = new UserRoleEntity(
                UUID.randomUUID().toString(),
                userId.value().toString(),
                roleRef,
                systemClock.instant()
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
