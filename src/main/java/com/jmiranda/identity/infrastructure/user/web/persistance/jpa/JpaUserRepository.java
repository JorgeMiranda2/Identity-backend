package com.jmiranda.identity.infrastructure.user.web.persistance.jpa;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.role.model.RoleId;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.HumanUser;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.domain.user.repository.UserRepository;
import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.LoginEntity;
import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.SpringDataLoginRepository;
import com.jmiranda.identity.infrastructure.user.web.persistance.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataRepository;
    private final SpringDataLoginRoleRepository springDataLoginRoleRepository;
    private final SpringDataLoginRepository springDataLoginRepository;
    private final UserMapper mapper;

    public JpaUserRepository(
            SpringDataUserRepository springDataRepository,
            SpringDataLoginRoleRepository springDataLoginRoleRepository,
            SpringDataLoginRepository springDataLoginRepository,
            UserMapper mapper
    ) {
        this.springDataRepository = springDataRepository;
        this.springDataLoginRoleRepository = springDataLoginRoleRepository;
        this.springDataLoginRepository = springDataLoginRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(HumanUser user) {
        UserEntity entity = mapper.toEntity(user);
        springDataRepository.save(entity);
    }

    @Override
    public Optional<HumanUser> findById(UserId id) {
        UserEntity entity = springDataRepository.findById(id.value().toString())
                .orElseThrow();

        HumanUser user = mapper.toDomain(entity);

        // Obtener logins

        Optional<Set<LoginEntity>> loginEntity = springDataLoginRepository.findByUser_id(id.value().toString());


        return Optional.of(user);
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

