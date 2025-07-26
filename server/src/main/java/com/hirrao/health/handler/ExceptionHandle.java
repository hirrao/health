package com.hirrao.health.handler;

import com.hirrao.health.common.exception.ClientException;
import com.hirrao.health.common.reponse.Response;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Response<String>> handleExpiredJwtException(
            ExpiredJwtException e) {
        log.warn("JWT Token expired: {} Id: {}", e.getMessage(), e.getClaims()
                                                                  .getSubject());
        return Response.error(HttpStatus.UNAUTHORIZED, -1, "Token过期");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response<String>> handleAccessDeniedException(
            AccessDeniedException e) {
        log.warn("Access denied: {}", e.getMessage());
        return Response.error(HttpStatus.UNAUTHORIZED, -1,
                              "权限不足或者未登录");
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Response<String>> handleClientException(
            ClientException e) {
        log.warn("Client Error:", e);
        return Response.error(e.getStatusCode(), -1, e.getClientMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception e) {
        log.error("An error occurred:", e);
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, -1,
                              e.getMessage());
    }
}