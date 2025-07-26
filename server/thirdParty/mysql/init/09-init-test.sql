USE test;

CREATE TABLE IF NOT EXISTS `user`
(
    uid           BIGINT       NOT NULL PRIMARY KEY,
    email         VARCHAR(64)  NOT NULL,
    username      VARCHAR(64)  NOT NULL,
    salt_password VARCHAR(128) NOT NULL,
    sex           TINYINT      NOT NULL DEFAULT 0,
    birthday      DATE         NOT NULL DEFAULT (CURRENT_DATE),
    register_time TIMESTAMP    NOT NULL DEFAULT (CURRENT_TIMESTAMP),
    role          INT          NOT NULL DEFAULT 1,
    UNIQUE KEY `idx_email` (`email`),
    UNIQUE KEY `idx_user_name` (username)
);

CREATE TABLE IF NOT EXISTS `health_advice`
(
    id          BIGINT       NOT NULL PRIMARY KEY COMMENT '文章ID',
    title       VARCHAR(128) NOT NULL COMMENT '文章标题',
    image       VARCHAR(256) COMMENT '文章封面图片URL',
    content     TEXT         NOT NULL COMMENT '文章内容',
    author      BIGINT COMMENT '作者ID, 用户表的uid',
    create_time TIMESTAMP    NOT NULL DEFAULT (CURRENT_TIMESTAMP) COMMENT '创建时间',
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (author) REFERENCES user (uid) ON DELETE SET NULL,
    FULLTEXT KEY `idx_title` (title) WITH PARSER ngram,
    KEY `idx_create_time` (create_time DESC)
)