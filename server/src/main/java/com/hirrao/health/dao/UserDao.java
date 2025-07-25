package com.hirrao.health.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hirrao.health.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserDao extends IService<User> {
    User getByUsername(String username);

    User getByEmail(String email);

    User getByUid(Long uid);

    User addUser(String username, String password, String email);

    User updateUser(User user);

    String getNameById(Long id);

    Map<Long, String> getNamesByIds(List<Long> list);
}