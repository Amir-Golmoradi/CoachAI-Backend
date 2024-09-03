-- V1.3 Create Users Table
-- This table stores the main user information corresponding to the User entity in Java.

CREATE TABLE IF NOT EXISTS users
(
    user_id       BIGINT      NOT NULL DEFAULT nextval('user_id_sequence'),
    user_name     TEXT        NOT NULL,
    user_email    TEXT        NOT NULL,
    user_age      INTEGER     NOT NULL CHECK (user_age >= 0), -- Add a check constraint for age
    user_password TEXT        NOT NULL,
    user_gender   gender_enum NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id),
    CONSTRAINT unique_user_email UNIQUE (user_email)
);
