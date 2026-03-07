package com.jmiranda.identity.infrastructure.role.web.persistance.jpa;

import com.jmiranda.identity.domain.role.model.Code;
import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.model.RoleId;
import com.jmiranda.identity.domain.role.repository.RoleRepository;
import com.jmiranda.identity.infrastructure.role.web.persistance.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaRoleRepository implements RoleRepository {

    private final SpringDataRoleRepository springDataRepository;
    private final RoleMapper mapper;

    public JpaRoleRepository(
            SpringDataRoleRepository springDataRepository,
            RoleMapper mapper
    ) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Role role) {
        RoleEntity entity = mapper.toEntity(role);
        springDataRepository.save(entity);
    }

    @Override
    public Optional<Role> findById(RoleId id) {
        return springDataRepository
                .findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findByCode(
            Code code
    ) {
        return springDataRepository
                .findByCode(code.value())
                .map(mapper::toDomain);
    }
}

