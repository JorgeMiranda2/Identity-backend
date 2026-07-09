package com.jmiranda.identity.domain.authority.model;

public class Authority {
    private final AuthorityId id;
    private final String name; // "GET:/users"

    public Authority(AuthorityId id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorityId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
