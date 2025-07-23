package com.hirrao.health.controller;

import com.hirrao.health.common.reponse.LoginResponse;
import com.hirrao.health.common.reponse.Response;
import com.hirrao.health.common.request.LoginRequest;
import com.hirrao.health.common.request.RegisterRequest;
import com.hirrao.health.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    private AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<LoginResponse>> register(
            @RequestBody RegisterRequest request) {
        return Response.ok(
                authService.register(request.username(), request.password(),
                                     request.email()));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(
            @RequestBody LoginRequest request) {
        return Response.ok(
                authService.login(request.username(), request.password()));
    }
}