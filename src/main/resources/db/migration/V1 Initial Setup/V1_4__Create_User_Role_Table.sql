CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT          NOT NULL,
    roles   user_roles_enum NOT NULL,

    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, roles),
    CONSTRAINT fk_user_roles_users FOREIGN KEY (user_id) REFERENCES users (user_id)
)