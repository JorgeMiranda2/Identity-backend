package com.jmiranda.identity.infrastructure.security.authorization;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.function.Supplier;
@Component
public class DynamicAuthorizationManager
        implements AuthorizationManager<RequestAuthorizationContext> {

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public AuthorizationDecision authorize(Supplier<? extends Authentication> authentication,
                                           RequestAuthorizationContext context) {

        HttpServletRequest request = context.getRequest();

        String method = request.getMethod();
        String path = request.getRequestURI();

        Authentication auth = authentication.get();

        boolean granted = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> match(authority, method, path));

        return new AuthorizationDecision(granted);
    }

    private boolean match(String authority, String method, String path) {

        String[] parts = authority.split(":");

        String authMethod = parts[0];
        String authPath = parts[1];

        if (!authMethod.equalsIgnoreCase(method)) return false;

        return matcher.match(authPath, path);
    }
}