package com.hirrao.health.security;

import com.hirrao.health.common.model.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    public boolean isLogin() {
        var principal = SecurityContextHolder.getContext()
                                             .getAuthentication()
                                             .getPrincipal();
        if (principal instanceof AuthUser authUser) {
            return authUser.role()
                           .notBanned();
        }
        return false;
    }
}
