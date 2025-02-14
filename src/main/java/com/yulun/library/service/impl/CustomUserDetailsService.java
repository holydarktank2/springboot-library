package com.yulun.library.service.impl;

import com.yulun.library.dao.UserDao;
import com.yulun.library.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;  // 用於查詢用戶的 DAO 類

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        // 根據用戶名（這裡假設是 phoneNumber）從數據庫查詢用戶
        User user = userDao.getUserByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with phone number: " + phoneNumber);
        }

        // 返回一個 org.springframework.security.core.userdetails.User 對象
        // 這裡的角色設置為 "USER" 需要根據實際情況進行修改
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getPhoneNumber())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
