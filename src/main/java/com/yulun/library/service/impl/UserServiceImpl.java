package com.yulun.library.service.impl;

import com.yulun.library.security.JwtUtil;
import com.yulun.library.dao.UserDao;
import com.yulun.library.dto.UserLoginRequest;
import com.yulun.library.dto.UserRegisterRequest;
import com.yulun.library.model.User;
import com.yulun.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtUtil jwtUtil;

    //使用 BCrypt 加鹽並雜湊密碼
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User getUserById(Integer userId) {
         return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊phoneNumber是否重複
        User user = userDao.getUserByPhoneNumber(userRegisterRequest.getPhoneNumber());
        if(user != null){
            logger.warn("該 Phone number {} 已經被註冊", userRegisterRequest.getPhoneNumber());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number already in use");
        }

        //使用BCrypt加鹽雜湊密碼
        String hashedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(hashedPassword);

        Integer userId = userDao.createUser(userRegisterRequest);

        return userId;
    }

    @Override
    public Map<String, Object> login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByPhoneNumber(userLoginRequest.getPhoneNumber());
        if(user == null){
            logger.warn("該 Phone number {} 不存在", userLoginRequest.getPhoneNumber());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        //使用BCrypt比對資料
        if(!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())){
            logger.warn("該 Phone number {} 密碼不正確", userLoginRequest.getPhoneNumber());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }

        //登入成功生成token
        String token = jwtUtil.generateToken(user.getPhoneNumber());

        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getUserId());
        response.put("token", token);

        return response;
    }
}
