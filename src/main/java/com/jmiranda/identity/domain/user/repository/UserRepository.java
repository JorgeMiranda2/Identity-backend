package com.jmiranda.identity.domain.user.repository;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.HumanUser;
import com.jmiranda.identity.domain.user.model.UserId;

import java.util.Optional;

public interface UserRepository {
    void save(HumanUser user);
    Optional<HumanUser> findById(UserId id);
    Optional<HumanUser> findByPersonalEmail(PersonalEmail personalEmail);
    Optional<HumanUser> findByIdentificationCode(IdentificationCode identificationCode);
}
