package com.jmiranda.identity.infrastructure.policy;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationTypeCode;
import com.jmiranda.identity.domain.Identification.policy.IdentificationValidationPolicy;

public class ConfigIdentificationValidationPolicy implements IdentificationValidationPolicy {
    @Override
    public boolean isValid(IdentificationTypeCode type, IdentificationCode rawCode) {
        return switch (type.value()) {
            case "CC" -> rawCode.value().matches("\\d{8,10}");
            case "TI" -> rawCode.value().matches("\\d{10,11}");
            case "PASSPORT" -> rawCode.value().matches("[A-Z0-9]{6,9}");
            default -> false;
        };
    }
}
