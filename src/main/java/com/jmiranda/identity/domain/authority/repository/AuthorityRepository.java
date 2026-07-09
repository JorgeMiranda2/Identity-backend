package com.jmiranda.identity.domain.authority.repository;

import com.jmiranda.identity.domain.authority.model.Authority;

import java.util.Optional;

public interface AuthorityRepository {
    void save(Authority authority);

    Optional<Authority> findByName(String name);
}
