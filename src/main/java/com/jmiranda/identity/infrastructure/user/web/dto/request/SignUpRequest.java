package com.jmiranda.identity.infrastructure.user.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(
        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        @Email
        String personalEmail,

        @Email
        String institutionalEmail,

        String phoneNumber,

        @NotNull
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        String birthDate,

        @NotBlank
        String identificationTypeId,

        @NotBlank
        String identificationCode,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$")
        String username,

        @NotBlank
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$")
        String password


) {
}
