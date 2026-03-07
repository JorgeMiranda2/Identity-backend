package com.jmiranda.identity.domain.role.model;

public final class Code {
        private final String value;

        private Code(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        public static Code of(String value) {
            if(value == null || value.isBlank()) {
                throw new IllegalArgumentException("code.required");
            }
            return new Code(value);
        }
}
