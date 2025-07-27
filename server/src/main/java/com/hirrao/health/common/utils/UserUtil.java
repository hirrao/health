package com.hirrao.health.common.utils;

import com.hirrao.health.common.model.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static AuthUser get() {
        return (AuthUser) SecurityContextHolder.getContext()
                                               .getAuthentication()
                                               .getPrincipal();
    }
}
