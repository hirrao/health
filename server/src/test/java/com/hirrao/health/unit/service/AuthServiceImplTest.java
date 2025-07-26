package com.hirrao.health.unit.service;

import com.hirrao.health.dao.UserDao;
import com.hirrao.health.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private AuthServiceImpl authService;

    @Nested
    class ResetPasswordByPasswordTest {
        @Test
        void resetPasswordByPassword() {

        }
    }
}
