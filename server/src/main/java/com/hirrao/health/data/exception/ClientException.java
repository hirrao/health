package com.hirrao.health.data.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientException extends RuntimeException {
    private final HttpStatus statusCode;

    public ClientException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

}