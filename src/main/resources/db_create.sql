-- Table: Users
CREATE TABLE users
(
    id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE ,
    password VARCHAR(256) NOT NULL
);
-- Table: Roles
CREATE TABLE roles
(
    id BIGINT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Table for mapping user and roles: users_roles
CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL ,
    role_id BIGINT NOT NULL ,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
);

INSERT INTO roles VALUE (1, 'ADMIN');
INSERT INTO roles VALUE (2, 'USER');

