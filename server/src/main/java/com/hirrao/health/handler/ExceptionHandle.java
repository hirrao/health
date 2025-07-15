package com.hirrao.health.handler;

import com.hirrao.health.common.reponse.Response;
import com.hirrao.health.common.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Response<String>> handleClientException(
            ClientException e) {
        log.warn("Client Error:", e);
        return Response.error(e.getStatusCode(), -1, e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception e) {
        log.error("An error occurred: {}", e.getMessage());
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, -1,
                              e.getMessage(), null);
    }
}