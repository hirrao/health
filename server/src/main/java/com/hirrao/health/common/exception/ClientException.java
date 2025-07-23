package com.hirrao.health.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String clientMessage;

    public ClientException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.clientMessage = message;
    }

    public ClientException(HttpStatus statusCode, String message,
                           String logMessage) {
        super(message);
        this.statusCode = statusCode;
        this.clientMessage = logMessage;
    }
}