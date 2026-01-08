package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.valueobject.InstitutionalEmail;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;

import java.time.Instant;
import java.util.Objects;

public final class HumanUser extends User {

    private FirstName firstName;
    private LastName lastName;
    private PersonalEmail personalEmail;
    private InstitutionalEmail institutionalEmail;
    private PhoneNumber phoneNumber;
    private BirthDate birthDate;
    private Identification identification;

    private HumanUser(UserId id, Instant createdAt, FirstName firstName, LastName lastName,
                 PersonalEmail personalEmail, InstitutionalEmail institutionalEmail,
                 PhoneNumber phoneNumber, BirthDate birthDate, Identification identification) {

        super(id, Objects.requireNonNull(createdAt));
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.personalEmail = Objects.requireNonNull(personalEmail);
        this.institutionalEmail = institutionalEmail;
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        this.birthDate = Objects.requireNonNull(birthDate);
        this.identification = Objects.requireNonNull(identification);

    }

    public static HumanUser create(
            FirstName firstName,
            LastName lastName,
            PersonalEmail personalEmail,
            InstitutionalEmail institutionalEmail,
            PhoneNumber phone,
            BirthDate birthDate,
            Identification identification
    ) {
        return new HumanUser(
                null,
                Instant.now(),
                firstName,
                lastName,
                personalEmail,
                institutionalEmail,
                phone,
                birthDate,
                identification
        );
    }

    public static HumanUser rehydrate(
            UserId id,
            Instant createdAt,
            FirstName firstName,
            LastName lastName,
            PersonalEmail personalEmail,
            InstitutionalEmail institutionalEmail,
            PhoneNumber phone,
            BirthDate birthDate,
            Identification identification
    ) {
        return new HumanUser(
                id,
                createdAt,
                firstName,
                lastName,
                personalEmail,
                institutionalEmail,
                phone,
                birthDate,
                identification
        );
    }

    @Override
    public UserId getId() {
        return super.getId();
    }
}
