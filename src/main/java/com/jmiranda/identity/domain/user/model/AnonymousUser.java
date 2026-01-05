package com.jmiranda.identity.domain.user.model;

import java.time.Instant;

public final class AnonymousUser extends User {
    private static final AnonymousUser anonymousUser = new AnonymousUser();
    private static final UserId ANONYMOUS_ID = UserId.anonymous();
    private AnonymousUser() {
        super(ANONYMOUS_ID, Instant.EPOCH);
    }
}
