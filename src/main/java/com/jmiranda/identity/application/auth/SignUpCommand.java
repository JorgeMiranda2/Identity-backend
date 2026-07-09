package com.jmiranda.identity.application.auth;

public record SignUpCommand(
        String firstName,
        String lastName,
        String personalEmail,
        String institutionalEmail,
        String phoneNumber,
        String birthDate,
        String identificationTypeId,
        String identificationCode,
        String username,
        String password
) {
}
