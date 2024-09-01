CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    version   INTEGER,
    login     VARCHAR(63),
    password  VARCHAR(63),
    email     VARCHAR(127),
    is_active BOOL
);