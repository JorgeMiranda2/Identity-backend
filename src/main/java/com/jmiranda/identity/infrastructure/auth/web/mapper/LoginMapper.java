package com.jmiranda.identity.infrastructure.auth.web.mapper;

import com.jmiranda.identity.domain.auth.model.Login;
import com.jmiranda.identity.domain.auth.model.LoginId;
import com.jmiranda.identity.domain.auth.model.UserPasswordHash;
import com.jmiranda.identity.domain.auth.model.Username;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.LoginEntity;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {
    public LoginEntity toEntity(Login login) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setId(login.getId().value().toString());
        loginEntity.setUsername(login.getUsername().value());
        loginEntity.setPasswordHash(login.getPasswordHash().value());
        loginEntity.setUserId(login.getUserId().value().toString());
        loginEntity.setStateId(login.getStateId());
        loginEntity.setCreatedAt(login.getCreatedAt());
        loginEntity.setUpdatedAt(login.getUpdatedAt());
        return loginEntity;
    }

    public Login toDomain(LoginEntity entity) {
        return Login.from(
                LoginId.of(entity.getId()),
                Username.of(entity.getUsername()),
                UserPasswordHash.of(entity.getPasswordHash()),
                UserId.of(entity.getUserId()),
                entity.getStateId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
