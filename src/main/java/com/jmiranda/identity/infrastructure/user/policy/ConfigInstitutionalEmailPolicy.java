package com.jmiranda.identity.infrastructure.user.policy;

import com.jmiranda.identity.domain.shared.policy.InstitutionalEmailPolicy;


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
