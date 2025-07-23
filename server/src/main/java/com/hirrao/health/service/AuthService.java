package com.hirrao.health.service;

import com.hirrao.health.common.reponse.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponse register(String username, String email, String password);

    LoginResponse login(String username, String password);

    void resetPassword(String username, String oldPassword,
                          String newPassword);
}
