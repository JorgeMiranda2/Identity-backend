package com.jmiranda.identity.infrastructure.auth.web.mapper;

import com.jmiranda.identity.domain.auth.model.Login;
import com.jmiranda.identity.domain.auth.model.LoginId;
import com.jmiranda.identity.domain.auth.model.UserPasswordHash;
import com.jmiranda.identity.domain.auth.model.Username;
import com.jmiranda.identity.domain.shared.valueobject.StateId;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.infrastructure.auth.web.persistance.jpa.LoginEntity;
import com.jmiranda.identity.infrastructure.state.web.persistance.jpa.StateEntity;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {
    public LoginEntity toEntity(Login login) {
        LoginEntity loginEntity = new LoginEntity();

        // Create a StateEntity and set its ID based on the StateId from the Login domain model
        StateEntity state = new StateEntity();
        state.setId(login.getStateId().value());

        loginEntity.setId(login.getId().value().toString());
        loginEntity.setUsername(login.getUsername().value());
        loginEntity.setPasswordHash(login.getPasswordHash().value());
        loginEntity.setUserId(login.getUserId().value().toString());
        loginEntity.setState(state);
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
                StateId.of(entity.getState().getId()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
