package com.yulun.library.dao.impl;

import com.yulun.library.dao.BorrowingRecordDao;
import com.yulun.library.model.BorrowingRecord;
import com.yulun.library.rowmapper.BorrowingRecordRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BorrowingRecordDaoImpl implements BorrowingRecordDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public BorrowingRecord getRecordById(Integer recordId){
        String sql = "SELECT record_id, user_id, inventory_id, borrowing_time, return_time " +
                " FROM borrowing_record WHERE record_id = :recordId ";

        Map<String, Object> map = new HashMap<>();
        map.put("recordId", recordId);

        List<BorrowingRecord> borrowingRecordList = namedParameterJdbcTemplate.query(sql, map, new BorrowingRecordRowMapper());

        if(borrowingRecordList.size() > 0){
            return borrowingRecordList.get(0);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<BorrowingRecord> getRecords(Integer userId) {
        String sql = "SELECT record_id, user_id, inventory_id, borrowing_time, return_time " +
                " FROM borrowing_record WHERE user_id = :userId ";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<BorrowingRecord> borrowingRecordList = namedParameterJdbcTemplate.query(sql, map, new BorrowingRecordRowMapper());

        return borrowingRecordList;
    }

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

    @Override
    public void updateRecord(Integer recordId) {
        String sql = "UPDATE borrowing_record SET return_time = :returnTime " +
                " WHERE record_id = :recordId";

        Map<String, Object> map = new HashMap<>();
        map.put("recordId", recordId);

        Date now = new Date();
        map.put("returnTime", now);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
