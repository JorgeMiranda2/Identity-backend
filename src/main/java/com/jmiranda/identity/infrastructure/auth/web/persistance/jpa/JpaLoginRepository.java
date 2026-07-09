package com.jmiranda.identity.infrastructure.auth.web.persistance.jpa;

import com.jmiranda.identity.domain.auth.model.Login;
import com.jmiranda.identity.domain.auth.repository.LoginRepository;
import com.jmiranda.identity.infrastructure.auth.web.mapper.LoginMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaLoginRepository implements LoginRepository {

    private final SpringDataLoginRepository springDataRepository;
    private final LoginMapper loginMapper;

    public JpaLoginRepository(SpringDataLoginRepository springDataRepository, LoginMapper mapper) {
        this.springDataRepository = springDataRepository;
        this.loginMapper = mapper;
    }

    @Override
    public void save(Login login) {
        springDataRepository.save(loginMapper.toEntity(login));
    }

    @Override
    public boolean existsByUsername(String username) {
        return springDataRepository.existsByUsername(username);
    }

    @Override
    public Optional<Login> findByUsername(String username) {
        return springDataRepository.findByUsername(username)
                .map(loginMapper::toDomain);
    }
}
