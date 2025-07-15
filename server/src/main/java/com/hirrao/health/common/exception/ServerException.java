package com.hirrao.health.common.exception;

import lombok.Getter;

@Getter
public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
}