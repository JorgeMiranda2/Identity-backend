-- =========================================
-- STATES
-- =========================================
INSERT INTO states (id, name, description, created_at)
VALUES
(1, 'ACTIVE', 'Active record', NOW()),
(2, 'INACTIVE', 'Inactive record', NOW()),
(3, 'LOCKED', 'Locked by security policy', NOW());

-- =========================================
-- ROLES (BUSINESS POSITIONS)
-- =========================================
INSERT INTO roles (id, code, name, state_id, created_at)
VALUES
('a1b2c3d4-cc01-4f1a-9a11-000000000001','USER', 'Standard User', 1, now()),
('a1b2c3d4-cc01-4f1a-9a11-000000000002','ADMIN', 'System Administrator', 1, now());

-- =========================================
-- MODULES (FUNCTIONAL CAPABILITIES)
-- =========================================
INSERT INTO modules (id, code, name, description, state_id, created_at)
VALUES
('a1b2c3d4-cc01-4f1a-9a11-000000000001','MAIN', 'Main Application', 'Base access after login (dashboard, profile)', 1, now()),
('a1b2c3d4-cc01-4f1a-9a11-000000000002','USER_MANAGEMENT', 'User Management', 'Manage system users', 1, now()),
('a1b2c3d4-cc01-4f1a-9a11-000000000003','SECURITY_ADMIN', 'Security Administration', 'Manage roles, modules and permissions', 1, now());

-- =========================================
-- ROLE ↔ MODULE RELATIONSHIPS
-- =========================================

-- USER role → basic access
INSERT INTO role_modules (role_id, module_id)
VALUES
('a1b2c3d4-cc01-4f1a-9a11-000000000001', 'a1b2c3d4-cc01-4f1a-9a11-000000000001'); -- USER → MAIN

-- ADMIN role → full system access
INSERT INTO role_modules (role_id, module_id)
VALUES
('a1b2c3d4-cc01-4f1a-9a11-000000000002', 'a1b2c3d4-cc01-4f1a-9a11-000000000001'), -- ADMIN → MAIN
('a1b2c3d4-cc01-4f1a-9a11-000000000002', 'a1b2c3d4-cc01-4f1a-9a11-000000000002'), -- ADMIN → USER_MANAGEMENT
('a1b2c3d4-cc01-4f1a-9a11-000000000002', 'a1b2c3d4-cc01-4f1a-9a11-000000000003'); -- ADMIN → SECURITY_ADMIN
