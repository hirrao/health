#!/usr/bin/env bash

set -e

if [ -z "$APP_USER" ]; then
  APP_USER=hirrao
fi

if [ -z "$APP_PASSWORD" ]; then
  APP_PASSWORD=hirrao
fi

if [ -z "$APP_DATABASE" ]; then
  APP_DATABASE=hirrao
fi

mysql -v -u root -p"$MYSQL_ROOT_PASSWORD" <<-EOSQL
    CREATE DATABASE IF NOT EXISTS \`${APP_DATABASE}\` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

    CREATE DATABASE IF NOT EXISTS \`test\` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

    CREATE USER '${APP_USER}'@'%' IDENTIFIED BY '${APP_PASSWORD}';

    CREATE USER 'test'@'%' IDENTIFIED BY 'test';

    GRANT ALL PRIVILEGES ON \`${APP_DATABASE}\`.* TO '${APP_USER}'@'%';

    GRANT ALL PRIVILEGES ON \`test\`.* TO 'test'@'%';

    DELETE FROM mysql.user WHERE User='root' AND Host NOT IN ('localhost', '127.0.0.1', '::1');

    FLUSH PRIVILEGES;
EOSQL