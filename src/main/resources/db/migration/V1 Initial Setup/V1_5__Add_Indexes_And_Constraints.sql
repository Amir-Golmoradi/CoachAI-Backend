-- V1.5 Add Indexes and Constraints
-- Optimize query performance and ensure data integrity.
DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1
                       FROM pg_class c
                                JOIN pg_namespace n ON n.oid = c.relnamespace
                       WHERE c.relname = 'idx_users_email'
                         AND n.nspname = 'public') THEN
            CREATE INDEX idx_users_email ON users (user_email);
        END IF;
    END
$$;
