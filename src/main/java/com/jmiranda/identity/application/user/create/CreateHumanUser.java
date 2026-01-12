package com.jmiranda.identity.application.user.create;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationType;
import com.jmiranda.identity.domain.Identification.model.IdentificationTypeId;
import com.jmiranda.identity.domain.shared.policy.InstitutionalEmailPolicy;
import com.jmiranda.identity.domain.shared.valueobject.InstitutionalEmail;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.*;
import com.jmiranda.identity.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.UUID;

@Service
public class CreateHumanUser {
    private final UserRepository userRepository;
    private final InstitutionalEmailPolicy institutionalEmailPolicy;
    private final Clock systemClock;

    public CreateHumanUser(
            UserRepository userRepository,
            InstitutionalEmailPolicy institutionalEmailPolicy,
            Clock clock
    ) {
        this.userRepository = userRepository;
        this.institutionalEmailPolicy = institutionalEmailPolicy;
        this.systemClock = clock;
    }


    public UserId execute(CreateHumanUserCommand command) {

        HumanUser user = HumanUser.create(
                new FirstName(command.firstName()),
                new LastName(command.lastName()),
                new PersonalEmail(command.personalEmail()),
                command.institutionalEmail() != null ? InstitutionalEmail.of(command.institutionalEmail(), this.institutionalEmailPolicy) : null,
                command.phoneNumber() != null ? new PhoneNumber(command.phoneNumber()) : null,
                BirthDate.of(command.birthDate()),
                Identification.of(
                        IdentificationTypeId.of(command.identificationTypeId()),
                        IdentificationCode.of(command.identificationCode())),
                systemClock
        );
        userRepository.save(user);
        return user.getId();
    }

}
