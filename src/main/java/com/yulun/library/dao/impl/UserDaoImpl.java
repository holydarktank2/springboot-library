package com.yulun.library.dao.impl;

import com.yulun.library.dao.UserDao;
import com.yulun.library.dto.UserRegisterRequest;
import com.yulun.library.model.User;
import com.yulun.library.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserById(Integer userId) {
//        String sql = "SELECT user_id, phone_number, password, user_name, registration_time, last_login_time " +
//                " FROM user WHERE user_id = :userId";
        String sql = "CALL get_user_by_id(:userId)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size() > 0){
            return userList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
//        String sql = "SELECT user_id, phone_number, password, user_name, registration_time, last_login_time " +
//                " FROM user WHERE phone_number = :phoneNumber";
        String sql = "CALL get_user_by_phone_number(:phoneNumber)";

        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size() > 0){
            return userList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
//        String sql = "INSERT INTO user (phone_number, password, user_name, registration_time, last_login_time) " +
//                " VALUES(:phoneNumber, :password, :userName, :registrationTime, :lastLoginTime) ";
        String sql = "CALL insert_user(:phoneNumber, :password, :userName, :registrationTime, :lastLoginTime, @newUserId)";

        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", userRegisterRequest.getPhoneNumber());
        map.put("password", userRegisterRequest.getPassword());
        map.put("userName", userRegisterRequest.getUserName());

        Date now = new Date();
        map.put("registrationTime", now);
        map.put("lastLoginTime", now);

//        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map));

        Integer userId = namedParameterJdbcTemplate.getJdbcTemplate().queryForObject("SELECT @newUserId", Integer.class);

        return userId;
    }
}
