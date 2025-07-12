FROM mysql:8.4.5

MAINTAINER hirrao

ENV MYSQL_ROOT_PASSWORD=h6pWvbrKGMBYVk8V

COPY ./init/* /docker-entrypoint-initdb.d/

RUN chmod +x /docker-entrypoint-initdb.d/*

EXPOSE 3306