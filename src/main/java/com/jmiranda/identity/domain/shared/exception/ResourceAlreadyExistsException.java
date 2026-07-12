package com.jmiranda.identity.domain.shared.exception;

public class ResourceAlreadyExistsException extends DomainException {

    private final String message;

    private ResourceAlreadyExistsException(String field, String message) {
        super("RESOURCE_ALREADY_EXISTS", field);
        this.message = message;
    }

    public static ResourceAlreadyExistsException forField(String field) {
        return new ResourceAlreadyExistsException(
                field,
                String.format("El recurso con el campo '%s' ya existe.", field)
        );
    }

    @Override
    public String getMessage() {
        return message;
    }
}
