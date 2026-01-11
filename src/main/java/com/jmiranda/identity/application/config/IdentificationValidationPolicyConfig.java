package com.jmiranda.identity.application.config;

import com.jmiranda.identity.domain.Identification.policy.IdentificationValidationPolicy;
import com.jmiranda.identity.infrastructure.Identification.policy.ConfigIdentificationValidationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentificationValidationPolicyConfig {
    @Bean
    public IdentificationValidationPolicy identificationValidationPolicy() {
        return new ConfigIdentificationValidationPolicy();
    }
}
