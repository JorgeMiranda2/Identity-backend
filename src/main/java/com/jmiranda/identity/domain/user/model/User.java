package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.valueobject.InstitutionalEmail;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;

import java.time.Instant;
import java.util.Objects;

public class User {
    private UserId id;
    private final UserType type;
    private FirstName firstName;
    private LastName lastName;
    private PersonalEmail personalEmail;
    private InstitutionalEmail institutionalEmail;
    private PhoneNumber phoneNumber;
    private BirthDate birthDate;
    private final Instant createdAt;

    private User(UserId id, FirstName firstName, LastName lastName,
                PersonalEmail personalEmail, InstitutionalEmail institutionalEmail,
                PhoneNumber phoneNumber, BirthDate birthDate, UserType type) {

        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.type = Objects.requireNonNull(type);
        this.personalEmail = Objects.requireNonNull(personalEmail);
        this.institutionalEmail = institutionalEmail;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.createdAt = Instant.now();
        validateInvariants();
    }
    private void validateInvariants() {
        if (type == UserType.HUMAN) {
            if (phoneNumber == null || birthDate == null) {
                throw new IllegalStateException(
                        "Human users must have phone number and birth date"
                );
            }
        }
    }

    public static User createHumanUser(FirstName firstName, LastName lastName,
                                   PersonalEmail personalEmail, InstitutionalEmail institutionalEmail,
                                   PhoneNumber phoneNumber, BirthDate birthDate) {
        return new User(null, firstName, lastName, personalEmail,
                institutionalEmail, phoneNumber, birthDate, UserType.HUMAN);
    }

    public static User rehydrateUser(UserId id, FirstName firstName, LastName lastName,
                                     PersonalEmail personalEmail, InstitutionalEmail institutionalEmail,
                                     PhoneNumber phoneNumber, BirthDate birthDate, UserType type) {
        return new User(id, firstName, lastName, personalEmail,
                institutionalEmail, phoneNumber, birthDate, type);
    }
}
