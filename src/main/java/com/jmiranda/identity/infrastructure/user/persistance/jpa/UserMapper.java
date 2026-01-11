package com.jmiranda.identity.infrastructure.user.persistance.jpa;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationTypeId;
import com.jmiranda.identity.domain.shared.valueobject.InstitutionalEmail;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public HumanUser toDomain(UserEntity entity) {

        return HumanUser.restore(
                UserId.from(entity.getId()),
                new FirstName(entity.getFirstName()),
                new LastName(entity.getLastName()),
                new PersonalEmail(entity.getPersonalEmail()),
                entity.getInstitutionalEmail() != null
                        ? InstitutionalEmail.restore(entity.getInstitutionalEmail())
                        : null,
                new PhoneNumber(entity.getPhoneNumber()),
                BirthDate.of(entity.getBirthDate()),
                Identification.of(
                        IdentificationTypeId.of(entity.getIdentificationTypeId()),
                        IdentificationCode.of(entity.getIdentificationNumber())
                        ),
                entity.getCreatedAt()
        );
    }

    public UserEntity toEntity(HumanUser user) {
        // Implement mapping logic from HumanUser to UserEntity
        return null;
    }
}
