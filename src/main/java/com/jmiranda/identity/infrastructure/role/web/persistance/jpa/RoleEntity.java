package com.jmiranda.identity.infrastructure.role.web.persistance.jpa;

import com.jmiranda.identity.infrastructure.shared.persistance.jpa.base.AuditableEntity;
import com.jmiranda.identity.infrastructure.state.web.persistance.jpa.StateEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Table(name= "roles")
@Getter
@Setter
@Entity
public class RoleEntity extends AuditableEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    // Mapear correctamente la relación many-to-one con StateEntity en la columna state_id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;


    // No existe la entidad Module en el proyecto; mapear la relación many-to-many
    // usando los ids de módulos en la tabla de unión `role_modules`.
    @ElementCollection
    @CollectionTable(name = "role_modules", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "module_id", length = 36, columnDefinition = "CHAR(36)")
    private Set<String> modules;
}