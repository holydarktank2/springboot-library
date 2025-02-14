package com.yulun.library.dao.impl;

import com.yulun.library.dao.BorrowingRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class BorrowingRecordDaoImpl implements BorrowingRecordDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createRecord(Integer userId, Integer inventoryId) {
        String sql = "INSERT INTO borrowing_record (user_id, inventory_id, borrowing_time) " +
                " VALUES( :userId, :inventoryId, :borrowingTime)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("inventoryId", inventoryId);

        Date now = new Date();
        map.put("borrowingTime", now);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
