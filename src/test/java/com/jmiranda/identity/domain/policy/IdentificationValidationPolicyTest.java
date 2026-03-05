package com.jmiranda.identity.domain.policy; // Paquete de dominio

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationTypeCode;
import com.jmiranda.identity.domain.Identification.policy.IdentificationValidationPolicy;
import com.jmiranda.identity.infrastructure.policy.ConfigIdentificationValidationPolicy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Esto es un Test Unitario Puro (rápido como el rayo)
class ConfigIdentificationValidationPolicyTest {

    private final IdentificationValidationPolicy policy = new ConfigIdentificationValidationPolicy();

    @Test
    void should_validate_correct_cc() {
        // Given
        var type = IdentificationTypeCode.of("CC");
        var code = IdentificationCode.of("1234567890"); // 10 dígitos

        // When & Then
        assertTrue(policy.isValid(type, code), "CC de 8-10 dígitos debería ser válida");
    }

    @Test
    void should_invalidate_wrong_cc_format() {
        // Given
        var type = IdentificationTypeCode.of("CC");
        var code = IdentificationCode.of("ABC12345"); // Letras no permitidas

        // When & Then
        assertFalse(policy.isValid(type, code), "CC con letras debería ser inválida");
    }

    @Test
    void should_validate_passport() {
        // Given
        var type = IdentificationTypeCode.of("PASSPORT");
        var code = IdentificationCode.of("PE123456");

        // When & Then
        assertTrue(policy.isValid(type, code));
    }
}