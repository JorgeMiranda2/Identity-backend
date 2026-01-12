package com.jmiranda.identity.domain.shared.valueobject;


import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record PersonalEmail(String value) {
    public PersonalEmail {
        if (value == null) {
            throw new InvalidValueException("PersonalEmail cannot be null");
        }

        String trimmedEmail = value.trim();

        if (trimmedEmail.isBlank()) {
            throw new InvalidValueException("PersonalEmail cannot be blank");
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!trimmedEmail.matches(emailRegex)) {
            throw new InvalidValueException("PersonalEmail is not valid");
        }

        value = trimmedEmail;
    }

}
