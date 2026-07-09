package com.jmiranda.identity.infrastructure.security.exception;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String message, Throwable cause) {
        super(message, cause);
    }

}
