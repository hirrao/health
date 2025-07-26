package com.hirrao.health.controller;

import com.hirrao.health.common.model.AuthUser;
import com.hirrao.health.common.reponse.LoginResponse;
import com.hirrao.health.common.reponse.Response;
import com.hirrao.health.common.request.LoginRequest;
import com.hirrao.health.common.request.RegisterRequest;
import com.hirrao.health.common.request.ResetPasswordByPasswordRequest;
import com.hirrao.health.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<LoginResponse>> register(
            @RequestBody RegisterRequest request) {
        return Response.ok(
                authService.register(request.username(), request.email(),
                                     request.password()));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(
            @RequestBody LoginRequest request) {
        return Response.ok(
                authService.login(request.username(), request.password()));
    }

    /**
     * 根据旧密码重置密码, 需求用户已经登录在个人设定里面修改
     *
     * @param request 请求体
     * @return 成功为0失败为-1
     */
    @PostMapping("/reset-password/password")
    @PreAuthorize("@permissionService.login")
    public ResponseEntity<Response<Integer>> resetPasswordByPassword(
            @RequestBody ResetPasswordByPasswordRequest request) {
        var user = (AuthUser) SecurityContextHolder.getContext()
                                                   .getAuthentication()
                                                   .getPrincipal();
        authService.resetPassword(user.username(), request.oldPassword(),
                                  request.newPassword());
        return Response.ok(null);
    }
}