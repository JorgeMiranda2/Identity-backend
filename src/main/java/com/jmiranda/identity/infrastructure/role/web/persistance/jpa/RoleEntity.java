package com.jmiranda.identity.infrastructure.role.web.persistance.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Table(name= "roles")
@Getter
@Setter
@Entity
public class RoleEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    // No existe entidad State en el proyecto; mapear el id como columna simple
    @Column(name = "state_id", nullable = false)
    private Long stateId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    // No existe la entidad Module en el proyecto; mapear la relación many-to-many
    // usando los ids de módulos en la tabla de unión `role_modules`.
    @ElementCollection
    @CollectionTable(name = "role_modules", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "module_id", length = 36, columnDefinition = "CHAR(36)")
    private Set<String> modules;
}