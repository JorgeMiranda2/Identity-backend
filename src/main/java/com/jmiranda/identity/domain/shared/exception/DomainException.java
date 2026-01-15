package com.jmiranda.identity.domain.shared.exception;

public abstract class DomainException extends RuntimeException {

    private final String code;
    private final String field;

    protected DomainException(String code, String field) {
        super(code);
        this.code = code;
        this.field = field;
    }

    public String code() {
        return code;
    }

    public String field() {
        return field;
    }
}
