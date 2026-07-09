package com.jmiranda.identity.infrastructure.permission.web.persistance.jpa;

import com.jmiranda.identity.infrastructure.state.web.persistance.jpa.StateEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false, unique = true)
    private String authority; // "GET:/users"

    @Column(nullable = false)
    private String httpMethod;
    @Column(nullable = false)
    private String urlPattern;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;

    
}
