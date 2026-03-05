package com.jmiranda.identity.infrastructure.web.response;

import com.jmiranda.identity.infrastructure.web.error.ApiError;

public record ApiResponse<T>(
        boolean success,
        T data,
        ApiError error
) {}
