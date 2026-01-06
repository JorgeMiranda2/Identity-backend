package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationType;
import com.jmiranda.identity.domain.Identification.policy.IdentificationPolicy;
import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public final class Identification {
    private final IdentificationType type;
    private IdentificationCode code;

    private Identification(IdentificationType type, IdentificationCode code) {
        this.type = type;
        this.code = code;
    }

    public static Identification of(
            IdentificationType type,
            IdentificationCode code,
            IdentificationPolicy policy
    ) {
        if (!policy.isValid(type.getCode(), code)) {
            throw new InvalidValueException("identification.invalid");
        }
        return new Identification(type, code);
    }

    public IdentificationCode code() {
        return code;
    }

    public IdentificationType type() {
        return type;
    }
}
