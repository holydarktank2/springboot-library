package com.yulun.library.dao;

import com.yulun.library.dto.UserRegisterRequest;
import com.yulun.library.model.User;

public interface UserDao {

    User getUserById(Integer userId);
    User getUserByPhoneNumber(String phoneNumber);
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
