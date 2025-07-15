USE health;

CREATE TABLE IF NOT EXISTS `user`
(
    uid           BIGINT       NOT NULL PRIMARY KEY,
    email         VARCHAR(64)  NOT NULL,
    username      VARCHAR(64)  NOT NULL,
    salt_password VARCHAR(128) NOT NULL,
    sex           TINYINT      NOT NULL DEFAULT 0,
    birthday      DATE         NOT NULL DEFAULT '2024-08-31',
    register_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role          TINYINT      NOT NULL DEFAULT 0,
    UNIQUE KEY `idx_email` (`email`),
    UNIQUE KEY `idx_user_name` (username)
);