package com.jmiranda.identity.infrastructure.web.error;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static org.springframework.util.StringUtils.capitalize;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ---------------------------
    // VALIDACIONES (DTO)
    // ---------------------------

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return buildError(
                "VALIDATION_ERROR",
                message,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    // ---------------------------
    // JSON MAL FORMADO
    // ---------------------------

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleInvalidJson(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        return buildError(
                "INVALID_JSON",
                "Malformed JSON request",
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    // ---------------------------
    // REGLAS DE DOMINIO
    // ---------------------------

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ApiError> handleInvalidValue(
            InvalidValueException ex,
            HttpServletRequest request
    ) {
        String message = switch (ex.code()) {
            case "INVALID_FORMAT" ->
                    capitalize(ex.field()) + " has an invalid format";
            case "REQUIRED" ->
                    capitalize(ex.field()) + " is required";
            default ->
                    "Invalid value";
        };

        return buildError(
                "INVALID_VALUE",
                message,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    // ---------------------------
    // INTEGRIDAD (FK, UNIQUE)
    // ---------------------------

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        return buildError(
                "DATA_INTEGRITY_VIOLATION",
                "Invalid reference or duplicated value",
                HttpStatus.CONFLICT,
                request
        );
    }

    // ---------------------------
    // NO ENCONTRADO (opcional)
    // ---------------------------

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            EntityNotFoundException ex,
            HttpServletRequest request
    ) {
        return buildError(
                "NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    // ---------------------------
    // FALLBACK (último recurso)
    // ---------------------------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(
            Exception ex,
            HttpServletRequest request
    ) {
        // ⚠️ aquí sí puedes loguear stacktrace
        ex.printStackTrace();

        return buildError(
                "INTERNAL_ERROR",
                "Unexpected server error",
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    // ---------------------------
    // BUILDER
    // ---------------------------

    private ResponseEntity<ApiError> buildError(
            String code,
            String message,
            HttpStatus status,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(status)
                .body(new ApiError(
                        code,
                        message,
                        status.value(),
                        request.getRequestURI(),
                        Instant.now()
                ));
    }
}
