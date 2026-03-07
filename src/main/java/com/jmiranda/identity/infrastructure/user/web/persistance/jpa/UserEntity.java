package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.infrastructure.role.web.persistance.jpa.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "personal_email", nullable = false, unique = true)
    private String personalEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name= "identification_number", unique = true)
    private String identificationNumber;

    @Column(name= "identification_type_id", length = 36, columnDefinition = "CHAR(36)")
    private String identificationTypeId;

    @Column(name = "institutional_email", unique = true)
    private String institutionalEmail;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_login_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public UserEntity() {} // JPA

    public UserEntity(
            String id,
            String firstName,
            String lastName,
            String personalEmail,
            String institutionalEmail,
            Instant createdAt
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalEmail = personalEmail;
        this.institutionalEmail = institutionalEmail;
        this.createdAt = createdAt;
    }
}
