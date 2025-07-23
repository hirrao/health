package com.hirrao.health.service.impl;

import com.hirrao.health.common.exception.ClientException;
import com.hirrao.health.common.exception.ServerException;
import com.hirrao.health.common.reponse.LoginResponse;
import com.hirrao.health.dao.UserDao;
import com.hirrao.health.security.JWTUtil;
import com.hirrao.health.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author hirrao
 * 鉴权相关服务实现类
 * @see AuthService
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    private static final String ALLOWED_SYMBOLS = "!@#$%^&*()_+-=[]{}|;:,.<>/?~";
    private static final String PASSWORD_REGEX = "^(?!\\d+$)[a-zA-Z0-9" + Pattern.quote(
            ALLOWED_SYMBOLS) + "]{8,20}+$";
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Autowired
    AuthServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder,
                    JWTUtil jwtUtil) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 用户注册
     * 用户名规则：6-20位的字母、数字或下划线
     * 密码规则：8-20位，必须包含字母、数字和特殊符号
     */
    @Override
    public LoginResponse register(String username, String email,
                                  String password) {
        if (!username.matches("^[a-zA-Z0-9_]{6,20}+$")) {
            throw new ClientException(HttpStatus.UNPROCESSABLE_ENTITY,
                                      "用户名必须是6-20位的字母、数字或下划线");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new ClientException(HttpStatus.UNPROCESSABLE_ENTITY,
                                      "密码必须包含字母、数字和特殊符号，且长度在8-20位之间");
        }
        if (!email.matches(
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new ClientException(HttpStatus.UNPROCESSABLE_ENTITY,
                                      "邮箱格式不正确");
        }
        var user1 = userDao.getByUsername(username);
        var user2 = userDao.getByEmail(email);
        if (user1 != null || user2 != null) {
            throw new ClientException(HttpStatus.CONFLICT, "用户名已被占用");
        }
        var saltPassword = passwordEncoder.encode(password);
        var user = userDao.addUser(username, saltPassword, email);
        return new LoginResponse(jwtUtil.createToken(user));
    }

    @Override
    public LoginResponse login(String username, String password) {
        var user = userDao.getByUsername(username);
        if (user == null) {
            throw new ClientException(HttpStatus.UNAUTHORIZED,
                                      "用户名或密码错误");
        }
        if (!passwordEncoder.matches(password, user.getSaltPassword())) {
            throw new ClientException(HttpStatus.UNAUTHORIZED,
                                      "用户名或密码错误");
        }
        if (!user.getRole()
                 .notBanned()) {
            throw new ClientException(HttpStatus.FORBIDDEN, "用户已被禁用");
        }
        return new LoginResponse(jwtUtil.createToken(user));
    }

    @Override
    public void resetPassword(String username, String oldPassword,
                              String newPassword) {
        var user = userDao.getByUsername(username);
        if (user == null) {
            throw new ClientException(HttpStatus.UNAUTHORIZED,
                                      "一个不存在的用户通过了JWT验证",
                                      "用户不存在, 请立刻联系管理员");
        }
        if (!passwordEncoder.matches(oldPassword, user.getSaltPassword())) {
            throw new ClientException(HttpStatus.UNPROCESSABLE_ENTITY,
                                      "密码错误");
        }
        if (!newPassword.matches(PASSWORD_REGEX)) {
            throw new ClientException(HttpStatus.UNPROCESSABLE_ENTITY,
                                      "密码必须包含字母、数字和特殊符号，且长度在8-20位之间");
        }
        var saltPassword = passwordEncoder.encode(newPassword);
        user.setSaltPassword(saltPassword);
        var user2 = userDao.updateUser(user);
        if (!passwordEncoder.matches(newPassword, user2.getSaltPassword()))
            throw new ServerException("未知错误");
    }
}
