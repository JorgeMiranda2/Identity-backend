package com.jmiranda.identity.infrastructure.security.service;

import com.jmiranda.identity.infrastructure.security.exception.InvalidJwtException;
import com.jmiranda.identity.infrastructure.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    public JwtService(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
        this.secretKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtProperties.getSecret())
        );
    }
    /**
     * Generate JWT Token.
     */
    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(
                        now.plusMillis(jwtProperties.getExpiration())
                ))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extract username from token
     */
    public String extractUsername(String token) {
        return extractAllClaims(token)
                .getSubject();
    }

    /**
     * Extract expire date from token
     */
    public Date extractExpiration(String token) {

        return extractAllClaims(token)
                .getExpiration();
    }

    /**
     * Verify if token is valid.
     */
    public boolean isTokenValid(String token) {
        return !isExpired(token);
    }

    /**
     * Verify is token is valid and it's the correct user.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isExpired(token);
    }

    private Claims extractAllClaims(String token) {
        try {

            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (JwtException | IllegalArgumentException ex) {

            throw new InvalidJwtException("Invalid JWT token", ex);

        }
    }

    private boolean isExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }
}
