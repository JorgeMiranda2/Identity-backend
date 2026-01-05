package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.valueobject.InstitutionalEmail;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;

import java.time.Instant;
import java.util.Objects;

public sealed abstract class User permits HumanUser, SystemUser, AnonymousUser {
    private UserId id;
    private final Instant createdAt;

    public User(UserId id, Instant createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

}
