package com.jmiranda.identity.domain.Identification.model;

import java.util.Objects;

public final class IdentificationType {
    private final IdentificationTypeId id;
    private final IdentificationTypeCode code;
    private final IdentificationTypeName name;
    private final String description;

    private IdentificationType(IdentificationTypeId id, IdentificationTypeCode code, IdentificationTypeName name, String description) {
        this.id = id;
        this.code = Objects.requireNonNull(code);
        this.name = Objects.requireNonNull(name);
        this.description = description;
    }
    public static IdentificationType of(IdentificationTypeId id, IdentificationTypeCode code, IdentificationTypeName name, String description) {
        return new IdentificationType(id, code, name, description);
    }

    public IdentificationTypeCode getCode() {
        return code;
    }
}
