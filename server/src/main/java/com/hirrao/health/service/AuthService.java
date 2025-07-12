package com.hirrao.health.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String register(String username, String password, String email);
}
