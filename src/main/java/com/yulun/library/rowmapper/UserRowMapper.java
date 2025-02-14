package com.yulun.library.rowmapper;

import com.yulun.library.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setPassword(rs.getString("password"));
        user.setUserName(rs.getString("user_name"));
        user.setRegistrationTime(rs.getTimestamp("registration_time"));
        user.setLastLoginTime(rs.getTimestamp("last_login_time"));
        return user;
    }
}
