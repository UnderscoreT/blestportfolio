-- V0__Create_user_profile_table.sql
CREATE TABLE user_profile (
                              id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                              username VARCHAR(255) NOT NULL,
                              encrypted_password VARCHAR(255) NOT NULL,
                              email VARCHAR(255) UNIQUE NOT NULL,
                              created_on VARCHAR(20),
                              last_updated VARCHAR(20)
);

CREATE TABLE role (
                      id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
                            user_profile_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_profile_id, role_id),
                            FOREIGN KEY (user_profile_id) REFERENCES user_profile(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);
