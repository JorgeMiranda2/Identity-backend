package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.auth.model.Username;
import com.jmiranda.identity.domain.auth.services.PasswordHasher;
import com.jmiranda.identity.domain.shared.valueobject.StateId;

import java.time.Instant;

public final class UserLogin {
    private final Username username;
    private final PasswordHash passwordHash;
    private final UserId userId;
    private final StateId stateId;
    private final Instant createdAt;

    public UserLogin(UserId userId, Username username, PasswordHash passwordHash, StateId stateId, Instant createdAt) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.stateId = stateId;
        this.createdAt = createdAt;
    }






}
