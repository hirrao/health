package com.hirrao.health.security;

import com.hirrao.health.common.enums.RoleEnum;
import com.hirrao.health.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JWTUtilTest {
    private final JWTUtil jwtUtil = new JWTUtil(
            "HS512hirrao3uXXdI4dZJ4dnZLKa9zJtV9wL2bE4rD6cU8fG1hN3kM5pQ7s",
            604800L, "hirrao");

    @Test
    public void EncodeAndDecodeToken() {
        var user = new User("testUser", "test@test.com", "test");
        user.setUid(123456789L);
        user.setRole(RoleEnum.NORMAL);
        String token = jwtUtil.createToken(user);
        int uid = jwtUtil.decodeToken(token);
        Assertions.assertThat(uid)
                  .isEqualTo(123456789L);
    }
}
