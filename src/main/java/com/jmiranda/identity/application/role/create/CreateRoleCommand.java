package com.jmiranda.identity.application.role.create;

public record CreateRoleCommand(
        String code,
        String name
) {
}
