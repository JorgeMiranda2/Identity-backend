package com.jmiranda.identity.infrastructure.web.error;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;
import com.jmiranda.identity.infrastructure.web.response.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 1. DTO VALIDATIONS (Spring Validator)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return buildError("VALIDATION_ERROR", message, HttpStatus.BAD_REQUEST, request);
    }

    // 2. JSON ERROR
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidJson(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return buildError("INVALID_JSON", "Malformed JSON request body", HttpStatus.BAD_REQUEST, request);
    }

    // 3. DOMAIN RULES (DDD) - Simplificado
    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidValue(InvalidValueException ex, HttpServletRequest request) {
        // Confiamos en que el Dominio generó un mensaje útil
        return buildError(ex.getCode(), ex.getMessage(), HttpStatus.UNPROCESSABLE_CONTENT, request);
    }

    // 4. DATABASE INTEGRITY
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        // Logueamos porque esto suele ser un bug o un ataque de fuerza bruta
        log.warn("Integrity violation: {}", ex.getMessage());
        return buildError("DATA_INTEGRITY_VIOLATION", "Reference integrity error or duplicate key", HttpStatus.CONFLICT, request);
    }

    // 5. NOT FOUND
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return buildError("RESOURCE_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // 6. UNEXPECTED
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception ex, HttpServletRequest request) {
        // Logueamos el StackTrace completo SOLO aquí, en el servidor
        log.error("Unexpected error at {}: ", request.getRequestURI(), ex);

        return buildError("INTERNAL_SERVER_ERROR", "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ApiResponse<Void>> buildError(String code, String message, HttpStatus status, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                code,
                message,
                status.value(),
                request.getRequestURI(),
                Instant.now()
        );
        return ResponseEntity.status(status).body(new ApiResponse<>(false, null, apiError));
    }
}