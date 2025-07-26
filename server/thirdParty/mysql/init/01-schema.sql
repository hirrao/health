USE health;

CREATE TABLE IF NOT EXISTS `user`
(
    uid           BIGINT       NOT NULL PRIMARY KEY COMMENT '用户ID, 雪花',
    email         VARCHAR(64)  NOT NULL COMMENT '邮箱',
    username      VARCHAR(64)  NOT NULL COMMENT '用户名',
    salt_password VARCHAR(128) NOT NULL COMMENT '加盐加密后的密码',
    sex           TINYINT      NOT NULL DEFAULT 0 COMMENT '性别, Java枚举装潢为TINYINT存储',
    birthday      DATE         NOT NULL DEFAULT (CURRENT_DATE) COMMENT '生日, 默认当前日期',
    register_time TIMESTAMP    NOT NULL DEFAULT (CURRENT_TIMESTAMP) COMMENT '注册时间, 自动生成',
    role          INT          NOT NULL DEFAULT 1 COMMENT '用户角色, Java枚举',
    -- 对用户名和email建索引加快查询
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
    KEY `idx_update_time` (update_time DESC)
)