package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.HumanUser;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.domain.user.repository.UserRepository;
import com.jmiranda.identity.infrastructure.user.web.persistance.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataRepository;
    private final UserMapper mapper;

    public JpaUserRepository(
            SpringDataUserRepository springDataRepository,
            UserMapper mapper
    ) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(HumanUser user) {
        UserEntity entity = mapper.toEntity(user);
        springDataRepository.save(entity);
    }

    @Override
    public Optional<HumanUser> findById(UserId id) {
        return springDataRepository
                .findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<HumanUser> findByPersonalEmail(PersonalEmail personalEmail) {
        return springDataRepository
                .findByPersonalEmail(personalEmail.value())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<HumanUser> findByIdentificationCode(
            IdentificationCode identificationCode
    ) {
        return springDataRepository
                .findByIdentificationNumber(identificationCode.value())
                .map(mapper::toDomain);
    }
}

