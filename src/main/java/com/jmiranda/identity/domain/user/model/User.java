package com.jmiranda.identity.domain.user.model;

import java.time.Instant;


public sealed abstract class User permits HumanUser, SystemUser, AnonymousUser {
    private final UserId id;
    private final Instant createdAt;

    public User(UserId id, Instant createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }
    public UserId getId() {
        return id;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }

}
