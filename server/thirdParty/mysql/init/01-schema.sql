USE health;

CREATE TABLE IF NOT EXISTS `user`
(
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uid           BIGINT       NOT NULL,
    email         VARCHAR(64)  NOT NULL,
    user_name     VARCHAR(64)  NOT NULL,
    salt_password VARCHAR(128) NOT NULL,
    sex           TINYINT      NOT NULL DEFAULT 0,
    birthday      DATE         NOT NULL DEFAULT '2024-08-31',
    register_time BIGINT       NOT NULL,
    permission    TINYINT      NOT NULL DEFAULT 0,
    UNIQUE KEY `idx_uid` (`uid`),
    UNIQUE KEY `idx_email` (`email`),
    UNIQUE KEY `idx_user_name` (`user_name`)
);