package com.jmiranda.identity.application.user.create;

public record CreateHumanUserCommand(
        String firstName,
        String lastName,
        String personalEmail,
        String institutionalEmail,
        String phoneNumber,
        String birthDate,
        String identificationTypeId,
        String identificationCode
) {
}
