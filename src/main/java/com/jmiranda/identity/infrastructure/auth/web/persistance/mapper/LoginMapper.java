package com.jmiranda.identity.infrastructure.auth.web.persistance.mapper;

import com.jmiranda.identity.domain.auth.model.Login;
import com.jmiranda.identity.domain.auth.model.LoginId;
import com.jmiranda.identity.domain.auth.model.UserPasswordHash;
import com.jmiranda.identity.domain.auth.model.Username;
import com.jmiranda.identity.domain.shared.valueobject.StateId;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.LoginEntity;
import com.jmiranda.identity.infrastructure.state.web.persistance.jpa.StateEntity;
import com.jmiranda.identity.infrastructure.user.web.persistance.jpa.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginEntity toEntity(Login login) {
        LoginEntity entity = new LoginEntity();

        entity.setId(login.getId().value().toString());
        entity.setUsername(login.getUsername().value());
        entity.setPasswordHash(login.getPasswordHash().value());

        StateEntity state = new StateEntity();
        state.setId(login.getStateId().value());
        entity.setState(state);

        UserEntity user = new UserEntity();
        user.setId(login.getUserId().value().toString());
        entity.setUser(user);

        entity.setCreatedAt(login.getCreatedAt());
        entity.setUpdatedAt(login.getUpdatedAt());

        return entity;
    }

    public Login toDomain(LoginEntity entity) {
        return Login.from(
                LoginId.of(entity.getId()),
                Username.of(entity.getUsername()),
                UserPasswordHash.fromHash(entity.getPasswordHash()),
                UserId.of(entity.getUser().getId()),
                StateId.of(entity.getState().getId()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
