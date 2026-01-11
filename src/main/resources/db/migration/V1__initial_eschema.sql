
-- =========================
-- STATES
-- =========================
CREATE TABLE states (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
);


-- =========================
-- USERS (PROFILE)
-- =========================
CREATE TABLE users (
    id char(36) PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    phone_number VARCHAR(32),
    birth_date DATE,
    personal_email VARCHAR(256),
    institutional_email VARCHAR(256),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
);

CREATE UNIQUE INDEX ux_users_personal_email ON users(personal_email);

-- =========================
-- USER LOGIN / CREDENTIALS
-- =========================
CREATE TABLE user_logins (
    id char(36) PRIMARY KEY,
    username VARCHAR(256) NOT NULL,
    password_hash TEXT NOT NULL,
    state_id BIGINT NOT NULL,
    user_id char(36) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_login_state
        FOREIGN KEY (state_id) REFERENCES states(id),
    CONSTRAINT fk_login_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE UNIQUE INDEX ux_user_logins_username ON user_logins(username);
CREATE INDEX idx_user_logins_state ON user_logins(state_id);

-- =========================
-- ROLES (POSITION / BUSINESS ROLE)
-- =========================
CREATE TABLE roles (
    id char(36) PRIMARY KEY,
    code VARCHAR(64) NOT NULL,
    name VARCHAR(128) NOT NULL,
    state_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_roles_state
        FOREIGN KEY (state_id) REFERENCES states(id)
);

CREATE UNIQUE INDEX ux_roles_code ON roles(code);

-- =========================
-- MODULES
-- =========================
CREATE TABLE modules (
    id char(36) PRIMARY KEY,
    code VARCHAR(64) NOT NULL,
    name VARCHAR(128) NOT NULL,
    description TEXT,
    state_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_modules_state
        FOREIGN KEY (state_id) REFERENCES states(id)
);

CREATE UNIQUE INDEX ux_modules_code ON modules(code);

-- =========================
-- PERMISSIONS (AUTHORITIES)
-- =========================
CREATE TABLE permissions (
    id char(36) PRIMARY KEY,
    authority VARCHAR(128) NOT NULL,
    http_method VARCHAR(10) NOT NULL,
    url_pattern VARCHAR(255) NOT NULL,
    state_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_permissions_state
        FOREIGN KEY (state_id) REFERENCES states(id)
);

CREATE UNIQUE INDEX ux_permissions_authority ON permissions(authority);

-- =========================
-- ROLE ↔ MODULE
-- =========================
CREATE TABLE role_modules (
    role_id char(36) NOT NULL,
    module_id char(36) NOT NULL,
    PRIMARY KEY (role_id, module_id),
    CONSTRAINT fk_rm_role FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_rm_module FOREIGN KEY (module_id) REFERENCES modules(id)
);

CREATE INDEX idx_rm_module ON role_modules(module_id);

-- =========================
-- MODULE ↔ PERMISSION
-- =========================
CREATE TABLE module_permissions (
    module_id char(36) NOT NULL,
    permission_id char(36) NOT NULL,
    PRIMARY KEY (module_id, permission_id),
    CONSTRAINT fk_mp_module FOREIGN KEY (module_id) REFERENCES modules(id),
    CONSTRAINT fk_mp_permission FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

CREATE INDEX idx_mp_permission ON module_permissions(permission_id);

-- =========================
-- USER ↔ ROLE
-- =========================
CREATE TABLE user_roles (
    user_login_id char(36) NOT NULL,
    role_id char(36) NOT NULL,
    PRIMARY KEY (user_login_id, role_id),
    CONSTRAINT fk_ur_login FOREIGN KEY (user_login_id) REFERENCES user_logins(id),
    CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE INDEX idx_ur_role ON user_roles(role_id);

-- =========================
-- USER ↔ MODULE (OVERRIDE)
-- =========================
CREATE TABLE user_modules (
    user_login_id char(36) NOT NULL,
    module_id char(36) NOT NULL,
    PRIMARY KEY (user_login_id, module_id),
    CONSTRAINT fk_um_login FOREIGN KEY (user_login_id) REFERENCES user_logins(id),
    CONSTRAINT fk_um_module FOREIGN KEY (module_id) REFERENCES modules(id)
);

CREATE INDEX idx_um_module ON user_modules(module_id);
