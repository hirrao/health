package com.hirrao.health.common.model;

import com.hirrao.health.common.enums.RoleEnum;

public record AuthUser(Long uid, String username, String email, RoleEnum role) {
}
