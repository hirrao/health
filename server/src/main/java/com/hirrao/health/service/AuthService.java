package com.hirrao.health.service;

import com.hirrao.health.common.reponse.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponse register(String username, String password, String email);

    LoginResponse login(String username, String password);
}
