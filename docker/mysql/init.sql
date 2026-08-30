-- initialize two databases for dev and qa and create a shared app user
CREATE DATABASE IF NOT EXISTS cafemgmt_dev;
CREATE DATABASE IF NOT EXISTS cafemgmt_qa;

CREATE USER IF NOT EXISTS 'appuser'@'%' IDENTIFIED BY 'apppass';
GRANT ALL PRIVILEGES ON cafemgmt_dev.* TO 'appuser'@'%';
GRANT ALL PRIVILEGES ON cafemgmt_qa.* TO 'appuser'@'%';
FLUSH PRIVILEGES;

