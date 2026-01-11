package com.jmiranda.identity.infrastructure.user.persistance.jpa;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.HumanUser;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.domain.user.repository.UserRepository;

import java.util.Optional;

public class JpaUserRepository implements UserRepository {
    @Override
    public void save(HumanUser user) {

    }

    @Override
    public Optional<HumanUser> findById(UserId id) {
        return Optional.empty();
    }

    @Override
    public Optional<HumanUser> findByPersonalEmail(PersonalEmail personalEmail) {
        return Optional.empty();
    }

    @Override
    public Optional<HumanUser> findByIdentificationCode(IdentificationCode identificationCode) {
        return Optional.empty();
    }
}
