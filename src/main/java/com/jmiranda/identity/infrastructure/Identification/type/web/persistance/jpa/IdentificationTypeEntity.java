package com.jmiranda.identity.infrastructure.Identification.type.web.persistance.jpa;

import com.jmiranda.identity.infrastructure.shared.persistance.jpa.base.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "identification_types")
public class IdentificationTypeEntity extends AuditableEntity {
    @Id
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

     public IdentificationTypeEntity() {} // JPA

}
