package com.yulun.library.security;

import com.yulun.library.dao.UserDao;
import com.yulun.library.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        // 根據phoneNumber查詢用戶
        User user = userDao.getUserByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with phone number: " + phoneNumber);
        }

        // 角色設置為USER
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getPhoneNumber())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
