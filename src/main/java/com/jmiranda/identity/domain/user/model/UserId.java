package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public record UserId(Long userId) {


    public UserId {
        if(userId==null){
            throw new InvalidValueException("UserId can not be null");
        }
    }

    public static UserId of(Long value){
        return new UserId(value);
    }
}
