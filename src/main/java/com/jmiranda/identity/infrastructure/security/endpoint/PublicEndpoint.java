package com.jmiranda.identity.infrastructure.security.endpoint;

import org.springframework.http.HttpMethod;

public record PublicEndpoint(
        HttpMethod method,
        String path
) {}
