package com.jmiranda.identity.domain.shared.valueobject;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;
import com.jmiranda.identity.domain.shared.policy.InstitutionalEmailPolicy;

public final class InstitutionalEmail {
    private final String email;

    private InstitutionalEmail(String email) {

        if (email == null) {
            throw new InvalidValueException("institutionalEmail.null");
        }

        String trimmedEmail = email.trim();

        if (trimmedEmail.isBlank()) {
            throw new InvalidValueException("institutionalEmail.blank");
        }

        this.email = trimmedEmail;
    }

    public static InstitutionalEmail of(
            String rawEmail,
            InstitutionalEmailPolicy policy
    ) {
        if(policy == null) {
            throw new IllegalArgumentException("InstitutionalEmailPolicy is required to create InstitutionalEmail");
        }
        if (!policy.isValid(rawEmail)) {
            throw new InvalidValueException("institutionalEmail.format");
        }

        return new InstitutionalEmail(rawEmail);
    }

    public static InstitutionalEmail restore(String email) {
        return new InstitutionalEmail(email);
    }

    public String value() {
        return email;
    }
}
