package com.jmiranda.identity.domain.user.exception;

import com.jmiranda.identity.domain.shared.exception.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(Long userId) {
        super("404", "User with ID " + userId + " not found.");
    }
}
