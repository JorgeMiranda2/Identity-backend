package com.jmiranda.identity.infrastructure.security.config;

import com.jmiranda.identity.infrastructure.security.endpoint.PublicEndpoints;
import com.jmiranda.identity.infrastructure.security.filter.JwtAuthenticationFilter;
import com.jmiranda.identity.infrastructure.security.service.JwtService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            UserDetailsService userDetailsService,
            JwtService jwtService
    ) {
        return new JwtAuthenticationFilter(
                userDetailsService,
                jwtService
        );
    }
    @Bean
    public SecurityFilterChain filterChain(
    HttpSecurity http,
    @Qualifier("dynamicAuthorizationManager") AuthorizationManager<RequestAuthorizationContext> authManager,
    JwtAuthenticationFilter jwtAuthenticationFilter
   ) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .authorizeHttpRequests(auth -> {

                    PublicEndpoints.ENDPOINTS.forEach(endpoint ->
                            auth.requestMatchers(
                                    endpoint.method(),
                                    endpoint.path()
                            ).permitAll()
                    );

                    auth.anyRequest().access(authManager);
                })
                .build();
    }
}
