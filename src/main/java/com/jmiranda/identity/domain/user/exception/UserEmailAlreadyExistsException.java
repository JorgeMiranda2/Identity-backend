package com.jmiranda.identity.domain.user.exception;

import com.jmiranda.identity.domain.shared.exception.DomainException;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;

public class UserEmailAlreadyExistsException extends DomainException {

    public UserEmailAlreadyExistsException(PersonalEmail email) {
        super("415", "User.email", "EMAIL_ALREADY_EXISTS:" + email.value());
    }
}

