-- ==========================================
-- CAFE MANAGEMENT SYSTEM - MYSQL INITIALIZATION
-- ==========================================

-- The first database and application user are created
-- automatically by the MySQL Docker image using:
-- MYSQL_DATABASE
-- MYSQL_USER
-- MYSQL_PASSWORD

-- Create QA database
CREATE DATABASE IF NOT EXISTS cafe_management_qa;

-- Grant application user access to QA database
GRANT ALL PRIVILEGES ON cafe_management_qa.*
TO 'cafe_mgmt_srija'@'%';

FLUSH PRIVILEGES;