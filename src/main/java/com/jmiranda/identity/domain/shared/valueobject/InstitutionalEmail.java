package com.jmiranda.identity.domain.shared.valueobject;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record InstitutionalEmail(String email) {

    private final static String DOMAIN = "institution.edu";
    public InstitutionalEmail {
        if (email == null) {
            throw new InvalidValueException("InstitutionalEmail cannot be null");
        }

        String trimmedEmail = email.trim();

        if (trimmedEmail.isBlank()) {
            throw new InvalidValueException("InstitutionalEmail cannot be blank");
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@" + DOMAIN.replace(".", "\\.") + "$";
        if (!trimmedEmail.matches(emailRegex)) {
            throw new InvalidValueException("InstitutionalEmail must belong to the domain " + DOMAIN);
        }

        email = trimmedEmail;
    }
}
