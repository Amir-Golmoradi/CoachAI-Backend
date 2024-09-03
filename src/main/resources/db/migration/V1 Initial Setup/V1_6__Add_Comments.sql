-- V1.6 Add Comments for Better Documentation
COMMENT ON TABLE users IS 'Stores user information';
COMMENT ON COLUMN users.user_id IS 'Unique identifier for the user';
COMMENT ON COLUMN users.user_name IS 'Name of the user';
COMMENT ON COLUMN users.user_email IS 'Email address of the user, used as username';
COMMENT ON COLUMN users.user_age IS 'Age of the user';
COMMENT ON COLUMN users.user_password IS 'Hashed password of the user';
COMMENT ON COLUMN users.user_gender IS 'Gender of the user';

COMMENT ON TABLE user_roles IS 'Stores roles associated with users';
COMMENT ON COLUMN user_roles.user_id IS 'Reference to the user';
COMMENT ON COLUMN user_roles.roles IS 'Role assigned to the user';
