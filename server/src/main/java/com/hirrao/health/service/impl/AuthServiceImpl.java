package com.hirrao.health.service.impl;

import com.hirrao.health.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public Integer register(String username, String password, String email) {
        return 0;
    }
}
