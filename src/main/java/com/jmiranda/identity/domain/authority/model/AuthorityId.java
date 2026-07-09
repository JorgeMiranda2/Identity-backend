package com.jmiranda.identity.domain.authority.model;

import java.util.UUID;

public final class AuthorityId {

    private final UUID value;

    private AuthorityId(UUID value) {
        this.value = value;
    }

    public static AuthorityId of(String value) {
        return new AuthorityId(UUID.fromString(value));
    }

    public UUID value() {
        return value;
    }
}
