package com.jmiranda.identity.application.auth;

import com.jmiranda.identity.domain.Identification.model.IdentificationCode;
import com.jmiranda.identity.domain.Identification.model.IdentificationTypeId;
import com.jmiranda.identity.domain.auth.model.Login;
import com.jmiranda.identity.domain.auth.model.UserPasswordHash;
import com.jmiranda.identity.domain.auth.model.Username;
import com.jmiranda.identity.domain.auth.policy.PasswordPolicy;
import com.jmiranda.identity.domain.auth.policy.UsernamePolicy;
import com.jmiranda.identity.domain.auth.repository.LoginRepository;
import com.jmiranda.identity.domain.auth.services.PasswordHasher;
import com.jmiranda.identity.domain.role.model.Code;
import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.repository.RoleRepository;
import com.jmiranda.identity.domain.shared.exception.ResourceAlreadyExistsException;
import com.jmiranda.identity.domain.shared.policy.InstitutionalEmailPolicy;
import com.jmiranda.identity.domain.shared.valueobject.InstitutionalEmail;
import com.jmiranda.identity.domain.shared.valueobject.PersonalEmail;
import com.jmiranda.identity.domain.user.model.*;
import com.jmiranda.identity.domain.user.repository.UserRepository;
import com.jmiranda.identity.domain.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Service
public class SignUp {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final LoginRepository loginRepository;
    private final RoleRepository roleRepository;
    private final InstitutionalEmailPolicy institutionalEmailPolicy;
    private final UsernamePolicy usernamePolicy;
    private final PasswordPolicy passwordPolicy;
    private final PasswordHasher passwordHasher;
    private final Clock systemClock;
    private final String defaultRoleCode;

    public SignUp(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            LoginRepository loginRepository,
            RoleRepository roleRepository,
            InstitutionalEmailPolicy institutionalEmailPolicy,
            UsernamePolicy usernamePolicy,
            PasswordPolicy passwordPolicy,
            PasswordHasher passwordHasher,
            Clock clock,
            @Value("${spring.security.default-role-code}") String defaultRoleCode
    ) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.loginRepository = loginRepository;
        this.roleRepository = roleRepository;
        this.institutionalEmailPolicy = institutionalEmailPolicy;
        this.usernamePolicy = usernamePolicy;
        this.passwordPolicy = passwordPolicy;
        this.passwordHasher = passwordHasher;
        this.systemClock = clock;
        this.defaultRoleCode = defaultRoleCode;
    }

    @Transactional
    public UserId execute(SignUpCommand command) {
        // 1) Person
        HumanUser user = HumanUser.create(
                new FirstName(command.firstName()),
                new LastName(command.lastName()),
                new PersonalEmail(command.personalEmail()),
                command.institutionalEmail() != null
                        ? InstitutionalEmail.of(command.institutionalEmail(), this.institutionalEmailPolicy)
                        : null,
                command.phoneNumber() != null ? new PhoneNumber(command.phoneNumber()) : null,
                BirthDate.of(command.birthDate()),
                Identification.of(
                        IdentificationTypeId.of(command.identificationTypeId()),
                        IdentificationCode.of(command.identificationCode())),
                systemClock
        );
        userRepository.save(user);

        // 2) Credentials (Login)
        if (loginRepository.existsByUsername(command.username())) {
            throw ResourceAlreadyExistsException.forField("username");
        }
        Username username = Username.create(command.username(), usernamePolicy);
        UserPasswordHash passwordHash = UserPasswordHash.create(
                command.password(), passwordPolicy, passwordHasher);
        Login login = Login.create(user.getId(), username, passwordHash);
        loginRepository.save(login);

        // 3) Default role assignment
        Role defaultRole = roleRepository.findByCode(Code.of(defaultRoleCode))
                .orElseThrow(() -> new IllegalStateException("role.default.notFound:" + defaultRoleCode));
        userRoleRepository.assign(login.getId(), defaultRole.getId());

        return user.getId();
    }
}
