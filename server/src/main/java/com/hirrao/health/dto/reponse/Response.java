package com.hirrao.health.dto.reponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record Response<T>(int code, String message, T data) {
    public static <T> ResponseEntity<Response<T>> ok(T data) {
        return ResponseEntity.ok(new Response<>(0, "Success", data));
    }

    public static <T> ResponseEntity<Response<T>> error(HttpStatus status,
                                                        int code,
                                                        String message,
                                                        T data) {
        return ResponseEntity.status(status)
                             .body(new Response<>(code, message, data));
    }
}