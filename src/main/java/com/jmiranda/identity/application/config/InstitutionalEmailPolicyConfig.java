package com.jmiranda.identity.application.config;

import com.jmiranda.identity.domain.shared.policy.InstitutionalEmailPolicy;
import com.jmiranda.identity.infrastructure.user.policy.ConfigInstitutionalEmailPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstitutionalEmailPolicyConfig {

    @Bean
    public InstitutionalEmailPolicy institutionalEmailPolicy(
            @Value("${institution.email.domain}") String domain
    ) {
        return new ConfigInstitutionalEmailPolicy(domain);
    }
}
