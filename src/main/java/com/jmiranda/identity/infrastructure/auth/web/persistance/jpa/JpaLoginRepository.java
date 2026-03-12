package com.jmiranda.identity.infrastructure.auth.web.persistance.jpa;

import com.jmiranda.identity.domain.auth.model.Login;
import com.jmiranda.identity.domain.auth.repository.LoginRepository;
import com.jmiranda.identity.infrastructure.auth.web.mapper.LoginMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JpaLoginRepository implements LoginRepository {

    private final SpringDataLoginRepository springDataRepository;
    private final LoginMapper loginMapper;

    public JpaLoginRepository(SpringDataLoginRepository springDataRepository, LoginMapper mapper) {
        this.springDataRepository = springDataRepository;
        this.loginMapper = mapper;
    }

    @Override
    public boolean existsByUsername(String username) {
        return springDataRepository.existsByUsername(username);
    }

    @Override
    public Login findByUsername(String username) {

        return loginMapper.toDomain(springDataRepository.findByUsername(username));

    }
}
