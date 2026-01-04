package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

import java.time.LocalDate;

public record BirthDate(LocalDate value) {

    public BirthDate {
        if (value == null) {
            throw new InvalidValueException("user.birthDate.null");
        }

        if (value.isAfter(LocalDate.now())) {
            throw new InvalidValueException("user.birthDate.future");
        }
    }
}