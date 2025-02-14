package com.yulun.library.service;

import com.yulun.library.dto.UserLoginRequest;
import com.yulun.library.dto.UserRegisterRequest;
import com.yulun.library.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
    String login(UserLoginRequest userLoginRequest);
}
