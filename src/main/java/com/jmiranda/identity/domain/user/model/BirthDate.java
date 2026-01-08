package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class BirthDate {

    private final LocalDate value;

    private BirthDate(LocalDate value) {
        this.value = value;
        validate();
    }

    public static BirthDate of(String raw) {
        if (raw == null) {
            throw new InvalidValueException("birthDate.null");
        }

        try {
            return new BirthDate(LocalDate.parse(raw));
        } catch (DateTimeParseException e) {
            throw new InvalidValueException("birthDate.format");
        }
    }

    public static BirthDate of(LocalDate date) {
        if (date == null) {
            throw new InvalidValueException("birthDate.null");
        }
        return new BirthDate(date);
    }

    private void validate() {
        if (value.isAfter(LocalDate.now())) {
            throw new InvalidValueException("birthDate.future");
        }
    }

    public LocalDate value() {
        return value;
    }
}
