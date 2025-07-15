package com.hirrao.health.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.hirrao.health.data.enums.RoleEnum;
import com.hirrao.health.data.enums.SexEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @TableId
    private Long uid;
    private String username;
    private String email;
    private String saltPassword;
    private SexEnum sex;
    private String birthday;
    private LocalDateTime registerTime;
    private RoleEnum role;

    public User(String username, String email, String saltPassword) {
        this.username = username;
        this.email = email;
        this.saltPassword = saltPassword;
    }
}
