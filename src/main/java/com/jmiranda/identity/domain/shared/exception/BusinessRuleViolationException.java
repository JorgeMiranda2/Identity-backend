package com.jmiranda.identity.domain.shared.exception;

public class BusinessRuleViolationException extends DomainException {
    public BusinessRuleViolationException(String code, String field) {
        super(code, field);
    }
}
