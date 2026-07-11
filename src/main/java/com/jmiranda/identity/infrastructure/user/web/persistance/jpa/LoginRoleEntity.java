package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.domain.auth.model.AssignmentSource;
import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.LoginEntity;
import com.jmiranda.identity.infrastructure.role.web.persistance.jpa.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "login_roles")
public class LoginRoleEntity {

    @Id
    @Column(length = 36, columnDefinition = "CHAR(36)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_id", nullable = false)
    private LoginEntity login;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_source", nullable = false)
    private AssignmentSource assignmentSource;

    @Column(name = "assigned_at", nullable = false)
    private Instant assignedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_by")
    private LoginEntity assignedBy;

    public LoginRoleEntity(){}

    public LoginRoleEntity(
            String id,
            LoginEntity loginEntity,
            RoleEntity roleEntity,
            AssignmentSource assignmentSource,
            Instant assignedAt,
            LoginEntity assignedBy
    ){
        this.id = id;
        this.login = loginEntity;
        this.role = roleEntity;
        this.assignmentSource = assignmentSource;
        this.assignedAt = assignedAt;
        this.assignedBy = assignedBy;
    }

}
