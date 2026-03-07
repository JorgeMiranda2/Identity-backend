package com.jmiranda.identity.infrastructure.role.web.persistance.mapper;

import com.jmiranda.identity.domain.role.model.Code;
import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.model.RoleId;
import com.jmiranda.identity.domain.shared.valueobject.StateId;
import com.jmiranda.identity.infrastructure.role.web.persistance.jpa.RoleEntity;
import org.springframework.stereotype.Component;



@Component
public class RoleMapper {
    // Convert Entity to Domain
    public Role toDomain(RoleEntity entity) {

        return Role.restore(
                RoleId.of(entity.getId()),
                Code.of(entity.getName()),
                entity.getCode(),
                StateId.of(entity.getStateId()),
                entity.getCreatedAt()
        );
    }

    // Convert Domain to Entity
    public RoleEntity toEntity(Role role) {
        RoleEntity entity = new RoleEntity();

        entity.setId(role.getId().value().toString());
        entity.setName(role.getName());
        entity.setCode(role.getCode().value());
        entity.setStateId(role.getStateId().value());

        return entity;
    }
}
