package com.jmiranda.identity.infrastructure.web.error;

import java.time.Instant;

public record ApiError(
        String code,
        String message,
        int status,
        String path,
        Instant timestamp
) {}
