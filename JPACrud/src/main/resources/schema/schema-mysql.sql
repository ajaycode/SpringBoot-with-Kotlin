SET time_zone='+00:00';
--USE `TEST`;

DROP TABLE IF EXISTS Employee;

SET FOREIGN_KEY_CHECKS=0;
--ALTER TABLE IF EXISTS users_roles DROP CONSTRAINT fk_username;
--ALTER TABLE IF EXISTS users_roles DROP FOREIGN KEY;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS User;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE Employee (
  eid INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR (25),
  department VARCHAR (25)
);

CREATE TABLE users (
    username VARCHAR (50) PRIMARY KEY NOT NULL,

    password VARCHAR (50) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    uid BIGINT  NOT NULL DEFAULT 5,
    not_expired BOOLEAN NOT NULL DEFAULT TRUE,
    not_locked BOOLEAN NOT NULL DEFAULT TRUE,
    email VARCHAR (100) NOT NULL DEFAULT "hello@hello.com",
    first_name VARCHAR (100),
    last_name VARCHAR (100)
);

CREATE TABLE user_roles (
    user_role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR (50)  NOT NULL,
    role VARCHAR (20) NOT NULL,
    UNIQUE KEY uni_username_role (role, username),
    KEY fk_username_idx (username),
    CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE User (
    User_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    First_Name VARCHAR (50) NOT NULL,
    Last_Name  VARCHAR (50) NOT NULL,
    Username   VARCHAR (50) NOT NULL,
    Email      VARCHAR (50) NOT NULL,
    Password   VARCHAR (50) NOT NULL,
    Create_Time DATETIME DEFAULT CURRENT_TIMESTAMP,
    Modify_Time DATETIME 
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Information about users.' AUTO_INCREMENT=1 ;

