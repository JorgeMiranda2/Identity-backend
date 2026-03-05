package com.jmiranda.identity.domain.shared.valueobject;

public final class StateId {
    public static final StateId ACTIVE = StateId.of(1L);
    private final Long value;

    private StateId(Long value) {
        this.value = value;
    }

    public static StateId of(Long value) {
        return new StateId(value);
    }

    public Long value() {
        return value;
    }
}
