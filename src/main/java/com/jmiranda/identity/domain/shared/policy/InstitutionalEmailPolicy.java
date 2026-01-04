package com.jmiranda.identity.domain.shared.policy;

public interface InstitutionalEmailPolicy {
    boolean isValid(String email);
}
