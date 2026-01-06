package com.jmiranda.identity.domain.Identification.policy;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationTypeCode;

public interface IdentificationPolicy {
    boolean isValid(IdentificationTypeCode code, IdentificationCode rawCode);
}
