package com.jmiranda.identity.infrastructure.role.web.persistance.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class RoleEntity {

    @Id
    private Long id;

    private String name;

    @ManyToMany
    private Set<Module> modules;
}