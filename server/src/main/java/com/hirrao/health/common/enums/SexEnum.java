package com.hirrao.health.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum implements IEnum<Integer> {
    UNSET(0),
    MALE(1),
    FEMALE(2);
    @EnumValue
    private final int value;

    @Override
    public Integer getValue() {
        return value;
    }
}