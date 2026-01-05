package com.jmiranda.identity.domain.user.model;

import com.jmiranda.identity.domain.shared.exception.InvalidValueException;

public final class UserId {

    private final long id;
    private final static Long SYSTEM_ID = 0L;
    private final static Long ANONYMOUS_ID = -1L;
    private UserId(long id){
    this.id = id;
    }

    public static UserId of(Long value){
        if(value==null){
            throw new InvalidValueException("user.userId.null");
        }
        if (value <= 0) {
            throw new InvalidValueException("user.userId.invalid");
        }
        return new UserId(value);
    }

    public static UserId system(){
        return new UserId(SYSTEM_ID);
    }
    public static UserId anonymous(){
        return new UserId(ANONYMOUS_ID);
    }

    public Long value(){
        return this.id;
    }
}
