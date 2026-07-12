package com.jmiranda.identity.infrastructure.security.endpoint;

import org.springframework.http.HttpMethod;

import java.util.List;

public final class PublicEndpoints {

    private PublicEndpoints() {}

    public static final List<PublicEndpoint> ENDPOINTS = List.of(
            new PublicEndpoint(HttpMethod.POST, "/auth/login"),
            new PublicEndpoint(HttpMethod.POST, "/auth/signup"),
            new PublicEndpoint(HttpMethod.POST, "/auth/register")
    );
}
