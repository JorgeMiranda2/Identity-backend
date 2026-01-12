package com.jmiranda.identity.domain.user.exception;

import com.jmiranda.identity.domain.shared.exception.DomainException;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;

public class UserEmailAlreadyExistsException extends DomainException {

    public UserEmailAlreadyExistsException(PersonalEmail email) {
        super("User with email " + email.value() + " already exists.");
    }
}

