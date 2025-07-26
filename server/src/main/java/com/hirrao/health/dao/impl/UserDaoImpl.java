package com.hirrao.health.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hirrao.health.common.exception.ServerException;
import com.hirrao.health.dao.UserDao;
import com.hirrao.health.dao.mapper.UserMapper;
import com.hirrao.health.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {
    private final UserMapper userMapper;

    @Autowired
    UserDaoImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getByUsername(String username) {
        var wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }

    @Override
    public User getByEmail(String email) {
        var wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getEmail, email);
        return getOne(wrapper);
    }

    @Override
    public User getByUid(Long uid) {
        var wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUid, uid);
        return getOne(wrapper);
    }

    @Override
    public User addUser(String username, String password, String email) {
        var user = new User(username, email, password);
        var success = userMapper.insert(user);
        if (success <= 0) {
            throw new ServerException("注册失败");
        }
        return getByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        userMapper.updateById(user);
        return getByUid(user.getUid());
    }

    @Override
    public String getNameById(Long author) {
        var wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUid, author)
               .select(User::getUsername);
        var user = userMapper.selectOne(wrapper);
        return user != null ? user.getUsername() : null;
    }
}
