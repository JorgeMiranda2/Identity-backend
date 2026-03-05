package com.jmiranda.identity.domain.auth.policy;

public interface UsernamePolicy {
    boolean isValid(String username);

}
