package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.infrastructure.Identification.type.web.persistance.jpa.IdentificationTypeEntity;
import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.LoginEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_type_id", nullable = false)
    private IdentificationTypeEntity identificationType;

    @Column(name = "institutional_email", unique = true)
    private String institutionalEmail;


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
