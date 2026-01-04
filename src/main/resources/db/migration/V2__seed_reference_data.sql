-- =========================================
-- STATES
-- =========================================
INSERT INTO states (id, name, description)
VALUES
(1, 'ACTIVE', 'Active record'),
(2, 'INACTIVE', 'Inactive record'),
(3, 'LOCKED', 'Locked by security policy');

-- =========================================
-- ROLES (BUSINESS POSITIONS)
-- =========================================
INSERT INTO roles (code, name, state_id)
VALUES
('USER', 'Standard User', 1),
('ADMIN', 'System Administrator', 1);

-- =========================================
-- MODULES (FUNCTIONAL CAPABILITIES)
-- =========================================
INSERT INTO modules (code, name, description, state_id)
VALUES
('MAIN', 'Main Application', 'Base access after login (dashboard, profile)', 1),
('USER_MANAGEMENT', 'User Management', 'Manage system users', 1),
('SECURITY_ADMIN', 'Security Administration', 'Manage roles, modules and permissions', 1);


-- =========================================
-- SYSTEM USERS (INTERNAL)
-- =========================================
INSERT INTO users (id, first_name, last_name)
VALUES
(-1, 'SYSTEM', 'USER'),
(-2, 'ANONYMOUS', 'USER');


-- =========================================
-- ROLE ↔ MODULE RELATIONSHIPS
-- =========================================

-- USER role → basic access
INSERT INTO role_modules (role_id, module_id)
VALUES
(1, 1); -- USER → MAIN

-- ADMIN role → full system access
INSERT INTO role_modules (role_id, module_id)
VALUES
(2, 1), -- ADMIN → MAIN
(2, 2), -- ADMIN → USER_MANAGEMENT
(2, 3); -- ADMIN → SECURITY_ADMIN
