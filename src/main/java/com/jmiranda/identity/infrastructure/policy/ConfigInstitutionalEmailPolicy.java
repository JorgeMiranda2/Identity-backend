package com.jmiranda.identity.infrastructure.policy;

import com.jmiranda.identity.domain.shared.policy.InstitutionalEmailPolicy;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Value;

public class ConfigInstitutionalEmailPolicy implements InstitutionalEmailPolicy {
    private final String domain;

    public ConfigInstitutionalEmailPolicy(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean isValid(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@" + domain.replace(".", "\\.") + "$";
        return email != null && email.matches(regex);
    }
}
