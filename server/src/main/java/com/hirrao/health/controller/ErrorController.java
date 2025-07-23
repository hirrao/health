package com.hirrao.health.controller;

import com.hirrao.health.common.reponse.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {
    @GetMapping
    public ResponseEntity<Response<String>> error() {
        return Response.error(HttpStatus.NOT_FOUND, -1, "Not Found");
    }
}
