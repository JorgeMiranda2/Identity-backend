package com.jmiranda.identity.infrastructure.web.error;

import com.jmiranda.identity.domain.shared.exception.DomainException;
import com.jmiranda.identity.domain.shared.exception.ResourceAlreadyExistsException;
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
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 1. DTO VALIDATIONS (Spring Validator)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new FieldError(err.getField(), err.getDefaultMessage()))
                .toList();

        String message = fieldErrors.stream()
                .map(fe -> fe.field() + ": " + fe.message())
                .collect(Collectors.joining("; "));

        return buildError("VALIDATION_ERROR", message, HttpStatus.BAD_REQUEST, request, fieldErrors);
    }

    // 2. JSON ERROR
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidJson(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return buildError("INVALID_JSON", "Malformed JSON request body", HttpStatus.BAD_REQUEST, request);
    }

    // 3. DOMAIN RULES (DDD) - Cubre InvalidValueException, BusinessRuleViolationException, etc.
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleDomain(DomainException ex, HttpServletRequest request) {
        HttpStatus status = ex instanceof ResourceAlreadyExistsException
                ? HttpStatus.CONFLICT
                : HttpStatus.UNPROCESSABLE_CONTENT;

        List<FieldError> fieldErrors = ex.getField() != null
                ? List.of(new FieldError(ex.getField(), ex.getMessage()))
                : null;

        return buildError(ex.getCode(), ex.getMessage(), status, request, fieldErrors);
    }

    // 4. DATABASE INTEGRITY - Intenta mapear índices unique a un campo concreto
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.warn("Integrity violation: {}", ex.getMessage());

        String cause = ex.getMostSpecificCause().getMessage();
        if (cause != null) {
            if (cause.contains("username") || cause.contains("uk_login_username")) {
                return buildError("USERNAME_ALREADY_EXISTS", "Username is already taken", HttpStatus.CONFLICT, request,
                        List.of(new FieldError("username", "Username is already taken")));
            }
            if (cause.contains("personal_email") || cause.contains("uk_user_personal_email")) {
                return buildError("PERSONAL_EMAIL_ALREADY_EXISTS", "Personal email is already registered", HttpStatus.CONFLICT, request,
                        List.of(new FieldError("personalEmail", "Personal email is already registered")));
            }
            if (cause.contains("institutional_email") || cause.contains("uk_user_institutional_email")) {
                return buildError("INSTITUTIONAL_EMAIL_ALREADY_EXISTS", "Institutional email is already registered", HttpStatus.CONFLICT, request,
                        List.of(new FieldError("institutionalEmail", "Institutional email is already registered")));
            }
        }

        return buildError("DATA_INTEGRITY_VIOLATION", "Reference integrity error or duplicate key", HttpStatus.CONFLICT, request);
    }

    // 5. NOT FOUND
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return buildError("RESOURCE_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request){
        return buildError("ILLEGAL_ARGUMENT", ex.getMessage(), HttpStatus.UNPROCESSABLE_CONTENT, request);
    }

    // 6. UNEXPECTED
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception ex, HttpServletRequest request) {
        // Logueamos el StackTrace completo SOLO aquí, en el servidor
        log.error("Unexpected error at {}: ", request.getRequestURI(), ex);

        return buildError("INTERNAL_SERVER_ERROR", "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ApiResponse<Void>> buildError(String code, String message, HttpStatus status, HttpServletRequest request) {
        return buildError(code, message, status, request, null);
    }

    private ResponseEntity<ApiResponse<Void>> buildError(String code, String message, HttpStatus status, HttpServletRequest request, List<FieldError> fieldErrors) {
        ApiError apiError = new ApiError(
                code,
                message,
                status.value(),
                request.getRequestURI(),
                Instant.now(),
                fieldErrors
        );
        return ResponseEntity.status(status).body(new ApiResponse<>(false, null, apiError));
    }
}