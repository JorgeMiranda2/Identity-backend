package com.jmiranda.identity.infrastructure.state.web.persistance.jpa;

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
@Table(name = "states")
public class StateEntity extends AuditableEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

        public StateEntity() {} // JPA

        public StateEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

}
