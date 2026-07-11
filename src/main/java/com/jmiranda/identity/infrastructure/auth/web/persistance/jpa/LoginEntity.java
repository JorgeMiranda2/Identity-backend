package com.jmiranda.identity.infrastructure.auth.web.persistance.jpa;

import com.jmiranda.identity.domain.shared.valueobject.StateId;
import com.jmiranda.identity.infrastructure.shared.persistance.jpa.base.AuditableEntity;
import com.jmiranda.identity.infrastructure.state.web.persistance.jpa.StateEntity;
import com.jmiranda.identity.infrastructure.user.web.persistance.jpa.LoginRoleEntity;
import com.jmiranda.identity.infrastructure.user.web.persistance.jpa.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "logins")
@Getter
@Setter
public class LoginEntity extends AuditableEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;

    @OneToMany(mappedBy = "login", fetch = FetchType.LAZY)
    private Set<LoginRoleEntity> loginRoles = new HashSet<>();


}
