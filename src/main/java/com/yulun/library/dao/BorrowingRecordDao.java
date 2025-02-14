package com.yulun.library.dao;

public interface BorrowingRecordDao {
    void createRecord(Integer userId, Integer inventoryId);
}
