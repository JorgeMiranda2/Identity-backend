package com.jmiranda.identity.application.config;

import com.jmiranda.identity.domain.auth.policy.PasswordPolicy;
import com.jmiranda.identity.infrastructure.policy.ConfigPasswordPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordPolicyConfig {
    @Bean
    public PasswordPolicy passwordPolicy() {
        return new ConfigPasswordPolicy();
    }
}
