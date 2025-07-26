FROM redis:8.0.3-alpine
MAINTAINER hirrao

COPY ./conf/redis.conf /usr/local/etc/redis/redis.conf

CMD ["redis-server", "/usr/local/etc/redis/redis.conf"]