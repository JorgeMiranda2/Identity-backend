package com.jmiranda.identity.domain.auth.model;

import com.jmiranda.identity.domain.auth.policy.PasswordPolicy;
import com.jmiranda.identity.domain.auth.services.PasswordHasher;
import com.jmiranda.identity.domain.shared.valueobject.StateId;
import com.jmiranda.identity.domain.user.model.UserId;

import java.time.Instant;

public class Login {
    private final LoginId id;
    private final Username username;
    private UserPasswordHash password;
    private final UserId userId;
    private final StateId stateId;
    private final Instant createdAt;
    private Instant updatedAt;

    private Login(LoginId loginId, Username username, UserPasswordHash password, UserId userId, StateId stateId, Instant createdAt, Instant updatedAt) {
        this.id = loginId;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.stateId = stateId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Login create(UserId userId, Username username, UserPasswordHash password) {
        return new Login(
                LoginId.generate(),
                username,
                password,
                userId,
                StateId.ACTIVE, // Estado inicial por defecto
                Instant.now(),  // CreatedAt    
                null            // UpdatedAt
        );
    }

    public static Login from(LoginId id, Username username, UserPasswordHash password, UserId userId, StateId stateId, Instant createdAt, Instant updatedAt) {
        return new Login(id, username, password, userId, stateId, createdAt, updatedAt);
    }

    public void changePassword(String newRawPassword, PasswordPolicy policy, PasswordHasher hasher) {
        this.password = UserPasswordHash.create(newRawPassword, policy, hasher);
        this.updatedAt = Instant.now();
    }

    public LoginId getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public UserPasswordHash getPasswordHash() {
        return password;
    }

    public UserId getUserId() {
        return userId;
    }

    public StateId getStateId() {
        return stateId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
