package com.yulun.library.dao;

import com.yulun.library.model.BorrowingRecord;

import java.util.List;

public interface BorrowingRecordDao {

    BorrowingRecord getRecordById(Integer recordId);
    List<BorrowingRecord> getRecords(Integer userId);
    void createRecord(Integer userId, Integer inventoryId);
    void updateRecord(Integer recordId);
}
