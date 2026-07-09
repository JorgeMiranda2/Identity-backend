package com.jmiranda.identity.infrastructure.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {
    private String secret;
    private long expiration;


    public String getSecret() {
        return secret;
    }

    public long getExpiration(){
        return expiration;
    }

}
