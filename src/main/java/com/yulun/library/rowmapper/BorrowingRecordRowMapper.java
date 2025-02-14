package com.yulun.library.rowmapper;

import com.yulun.library.model.BorrowingRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowingRecordRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        BorrowingRecord borrowingRecord = new BorrowingRecord();

        borrowingRecord.setRecordId(rs.getInt("record_id"));
        borrowingRecord.setUserId(rs.getInt("user_id"));
        borrowingRecord.setInventoryId(rs.getInt("inventory_id"));
        borrowingRecord.setBorrowingTime(rs.getTimestamp("borrowing_time"));
        borrowingRecord.setReturnTime(rs.getTimestamp("return_time"));

        return borrowingRecord;
    }
}
