-- V1.2 Create Sequences
-- Create a sequence generator for user IDs, used for primary key generation.
CREATE SEQUENCE user_id_sequence
    INCREMENT BY 1
    START WITH 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


