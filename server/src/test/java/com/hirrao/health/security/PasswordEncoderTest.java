package com.hirrao.health.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
    private final SecurityConfig securityConfig = new SecurityConfig(null);
    private final PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

    @Test
    public void testPasswordEncoding() {
        String password = "testPassword";
        String encodedPassword = passwordEncoder.encode(password);
        Assertions.assertThat(encodedPassword)
                  .isNotEqualTo(password)
                  .matches(encoded -> passwordEncoder.matches(password,
                                                              encoded));
        System.out.println(passwordEncoder.encode("testPassword"));
    }
}
