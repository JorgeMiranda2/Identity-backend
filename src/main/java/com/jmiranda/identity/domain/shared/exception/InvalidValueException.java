package com.jmiranda.identity.domain.shared.exception;

public class InvalidValueException extends DomainException {

    // Guardamos el campo por si queremos enviarlo en el JSON de respuesta
    // (Opcional, pero útil para el frontend)
    private final String field;

    private InvalidValueException(String field, String code, String message) {
        super(code, message);
        this.field = field;
    }

    // Factory para formato inválido
    public static InvalidValueException invalidFormat(String field) {
        return new InvalidValueException(
                field,
                "INVALID_FORMAT",
                String.format("El campo '%s' no tiene un formato válido.", field) // Mensaje autogenerado
        );
    }

    // Factory para campos requeridos
    public static InvalidValueException required(String field) {
        return new InvalidValueException(
                field,
                "REQUIRED_FIELD",
                String.format("El campo '%s' es obligatorio.", field)
        );
    }

    // Getter para que el ExceptionHandler lo use
    public String getField() {
        return field;
    }
}