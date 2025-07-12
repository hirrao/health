package com.hirrao.health.data.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionEnum implements IEnum<Integer> {
    BANNED(-1),
    NORMAL(0),
    ADMIN(1),
    SUPER_ADMIN(2);
    private final int value;

    @Override
    public Integer getValue() {
        return value;
    }
}
