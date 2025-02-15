package com.yulun.library.service;

import com.yulun.library.dto.UserLoginRequest;
import com.yulun.library.dto.UserRegisterRequest;
import com.yulun.library.model.User;

import java.util.Map;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
    Map<String, Object> login(UserLoginRequest userLoginRequest);
}
