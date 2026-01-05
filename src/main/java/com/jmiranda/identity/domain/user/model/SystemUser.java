package com.jmiranda.identity.domain.user.model;

import java.time.Instant;

public final class SystemUser extends User {

    private static final SystemUser systemUser = new SystemUser();
    private static final UserId SYSTEM_ID = UserId.system();
    public SystemUser() {
        super(SYSTEM_ID, Instant.EPOCH);
    }
}
