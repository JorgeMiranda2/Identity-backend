package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationTypeId;
import com.jmiranda.identity.domain.Identification.policy.IdentificationValidationPolicy;
import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public final class Identification {
    private final IdentificationTypeId typeId;
    private IdentificationCode code;

    private Identification(IdentificationTypeId typeId, IdentificationCode code) {
        this.typeId = typeId;
        this.code = code;
    }

    public static Identification of(
            IdentificationTypeId typeId,
            IdentificationCode code
    ) {

        if (typeId == null) {
            throw new InvalidValueException("identification.type.null");
        }

        return new Identification(typeId, code);
    }

    public IdentificationCode code() {
        return code;
    }

}
