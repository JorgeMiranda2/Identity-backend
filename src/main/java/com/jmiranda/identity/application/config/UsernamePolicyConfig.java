package com.jmiranda.identity.application.config;

import com.jmiranda.identity.domain.auth.policy.UsernamePolicy;
import com.jmiranda.identity.infrastructure.policy.ConfigUsernamePolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsernamePolicyConfig {
    @Bean
    public UsernamePolicy usernamePolicy() {
        return new ConfigUsernamePolicy();
    }
}
