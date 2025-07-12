FROM node:22-slim AS builder
COPY . /web
WORKDIR /web
RUN npm i -g pnpm; \
    pnpm install --frozen-lockfile; \
    pnpm run build

FROM caddy:2.10.0-alpine
COPY Caddyfile /etc/caddy/Caddyfile
COPY --from=builder /web/dist /dist