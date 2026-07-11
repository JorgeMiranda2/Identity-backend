package com.jmiranda.identity.infrastructure.user.web.persistance.mapper;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationTypeId;
import com.jmiranda.identity.domain.auth.model.Login;
import com.jmiranda.identity.domain.shared.valueobject.InstitutionalEmail;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.*;
import com.jmiranda.identity.infrastructure.Identification.type.web.persistance.jpa.IdentificationTypeEntity;
import com.jmiranda.identity.infrastructure.auth.web.persistance.mapper.LoginMapper;
import com.jmiranda.identity.infrastructure.user.web.persistance.jpa.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final LoginMapper loginMapper;

    public UserMapper(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }
    // Convert Entity to Domain
    public HumanUser toDomain(UserEntity entity) {
        Set<Login> logins = entity.getLogins().stream()
            .map(loginMapper::toDomain)
            .collect(Collectors.toSet());

        return HumanUser.restore(
                UserId.of(entity.getId()),
                new FirstName(entity.getFirstName()),
                new LastName(entity.getLastName()),
                new PersonalEmail(entity.getPersonalEmail()),
                entity.getInstitutionalEmail() != null
                        ? InstitutionalEmail.restore(entity.getInstitutionalEmail())
                        : null,
                new PhoneNumber(entity.getPhoneNumber()),
                BirthDate.of(entity.getBirthDate()),
                Identification.of(
                        IdentificationTypeId.of(entity.getIdentificationType().getId()),
                        IdentificationCode.of(entity.getIdentificationNumber())
                        ),
                Set.of(), // Logins
                entity.getCreatedAt()
        );
    }

    // Convert Domain to Entity
    public UserEntity toEntity(HumanUser user) {
        UserEntity entity = new UserEntity();

        entity.setId(user.getId().value().toString());
        entity.setFirstName(user.getFirstName().value());
        entity.setLastName(user.getLastName().value());
        entity.setPersonalEmail(user.getPersonalEmail().value());

        entity.setInstitutionalEmail(
                user.getInstitutionalEmail() != null
                        ? user.getInstitutionalEmail().value()
                        : null
        );

        entity.setPhoneNumber(
                user.getPhoneNumber() != null
                        ? user.getPhoneNumber().value()
                        : null
        );

        entity.setBirthDate(user.getBirthDate().value());

        IdentificationTypeEntity identificationType = new IdentificationTypeEntity();
        identificationType.setId(user.getIdentification().getTypeId().value().toString());
        entity.setIdentificationType(
                identificationType
        );

        entity.setIdentificationNumber(
                user.getIdentification().getCode().value()
        );

        entity.setCreatedAt(user.getCreatedAt());

        return entity;
    }
}
