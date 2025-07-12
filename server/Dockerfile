FROM gradle:8.14.2-jdk21 AS BUILDER

COPY . /server
WORKDIR /server
RUN gradle --no-daemon installDist

FROM eclipse-temurin:21-jre-alpine
COPY --from=builder /server/build/install/health /server
ENV LANG C.UTF-8
CMD ["/server/bin/health"]