-- TABLE users
CREATE TABLE users_security
(
    id       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    age      INT          NOT NULL,
    email    VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

# Table: roles
CREATE TABLE roles
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
) ENGINE = InnoDB;

# Table for mapping users and roles: user_roles
CREATE TABLE