package com.hirrao.health.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum implements IEnum<Integer> {
    BANNED(-1),
    NORMAL(1),
    ADMIN(100),
    SUPER_ADMIN(1000);
    private final int value;

    @Override
    public Integer getValue() {
        return value;
    }

    public boolean notBanned() {
        return this.value > 0;
    }

    public boolean isAdmin() {
        return this.value > ADMIN.getValue();
    }
}
