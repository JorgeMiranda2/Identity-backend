
-- Creating identification types table

CREATE TABLE identification_types (
    id char(36) PRIMARY KEY,
    code VARCHAR(32) NOT NULL UNIQUE,
    name VARCHAR(64) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
);

-- Adding identification_type_id to users table
ALTER TABLE users
ADD COLUMN identification_number VARCHAR(64) NOT NULL
    AFTER institutional_email,
ADD COLUMN identification_type_id CHAR(36) NOT NULL
    AFTER identification_number,
ADD CONSTRAINT fk_users_identification_type
    FOREIGN KEY (identification_type_id)
    REFERENCES identification_types(id);

CREATE UNIQUE INDEX ux_users_identification
ON users (identification_type_id, identification_number);
