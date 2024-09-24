CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE DEVIL_FRUITS
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),  -- surrogate key
    code    VARCHAR(6) NOT NULL UNIQUE,
    name    VARCHAR(255) NOT NULL,
    type    VARCHAR(255) NOT NULL,
    ability VARCHAR(512) NOT NULL
);