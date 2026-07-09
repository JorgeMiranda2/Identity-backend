package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.infrastructure.role.web.persistance.jpa.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class UserRoleEntity {
    @Id
    @Column(length = 36, columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "user_id", nullable = false, columnDefinition = "CHAR(36)")
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false, columnDefinition = "CHAR(36)")
    private RoleEntity role;

    @Column(name = "assigned_at", nullable = false)
    private Instant assignedAt;

    @Column(name = "assigned_by", nullable = false)
    private String assignedBy;

    public UserRoleEntity() {}

    public UserRoleEntity(String id, String userId, RoleEntity role, Instant assignedAt) {
        this.id = id;
        this.userId = userId;
        this.role = role;
        this.assignedAt = assignedAt;
    }
}
