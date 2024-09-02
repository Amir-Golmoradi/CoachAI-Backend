CREATE TABLE users
(
    user_id    BIGSERIAL PRIMARY KEY,
    user_name  TEXT        NOT NULL,
    user_email TEXT        NOT NULL UNIQUE,
    user_age   INTEGER     NOT NULL,
    password   TEXT        NOT NULL,
    gender     VARCHAR(10) NOT NULL
);
