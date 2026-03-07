package com.jmiranda.identity.application.role.create;


import com.jmiranda.identity.domain.role.model.Code;
import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.model.RoleId;
import com.jmiranda.identity.domain.role.repository.RoleRepository;
import com.jmiranda.identity.domain.shared.valueobject.StateId;
import org.springframework.stereotype.Service;
import java.time.Clock;

@Service
public class CreateRole {
    private final RoleRepository roleRepository;
    private final Clock systemClock;

    public CreateRole(
            RoleRepository roleRepository,
            Clock clock
    ) {
        this.roleRepository = roleRepository;
        this.systemClock = clock;
    }


    public RoleId execute(CreateRoleCommand command) {
        Role role = Role.create(

                command.name(),
                Code.of(command.code()),
                StateId.ACTIVE,
                systemClock
        );

        roleRepository.save(role);
        return role.getId();
    }

}
