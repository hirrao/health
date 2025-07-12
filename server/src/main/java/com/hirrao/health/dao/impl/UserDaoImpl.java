package com.hirrao.health.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hirrao.health.dao.UserDao;
import com.hirrao.health.dao.mapper.UserMapper;
import com.hirrao.health.entity.User;

public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {
}
