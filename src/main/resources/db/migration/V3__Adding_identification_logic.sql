
-- Creating identification types table

CREATE TABLE identification_types (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(32) NOT NULL UNIQUE,
    name VARCHAR(64) NOT NULL UNIQUE,
    description TEXT
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
);

-- Adding identification_type_id to users table
ALTER TABLE users
ADD COLUMN identification_number VARCHAR(64) NOT NULL,
ADD COLUMN identification_type_id BIGINT NOT NULL,
ADD CONSTRAINT fk_users_identification_type
    FOREIGN KEY (identification_type_id) REFERENCES identification_types(id);
ADD UNIQUE INDEX ux_users_identification ON (identification_type_id, identification_number);