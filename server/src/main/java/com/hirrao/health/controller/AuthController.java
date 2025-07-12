package com.hirrao.health.controller;


import com.hirrao.health.dto.reponse.Response;
import com.hirrao.health.dto.request.RegisterRequest;
import com.hirrao.health.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    private AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<Integer>> register(
            @RequestBody RegisterRequest request) {
        return Response.ok(
                authService.register(request.username(), request.password(),
                                     request.email()));
    }
}