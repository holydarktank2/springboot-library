package com.yulun.library.service.impl;

import com.yulun.library.dao.BorrowingRecordDao;
import com.yulun.library.dao.InventoryDao;
import com.yulun.library.dao.UserDao;
import com.yulun.library.dto.BorrowBook;
import com.yulun.library.dto.BorrowBookRequest;
import com.yulun.library.dto.ReturnBook;
import com.yulun.library.dto.ReturnBookRequest;
import com.yulun.library.model.BorrowingRecord;
import com.yulun.library.model.Inventory;
import com.yulun.library.model.User;
import com.yulun.library.service.BorrowBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class BorrowBookServiceImpl implements BorrowBookService {

    private static final Logger logger = LoggerFactory.getLogger(BorrowBookServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private InventoryDao inventoryDao;
    @Autowired
    private BorrowingRecordDao borrowingRecordDao;

    @Override
    public List<BorrowingRecord> getRecords(Integer userId) {
        return borrowingRecordDao.getRecords(userId);
    }

    @Transactional
    @Override
    public void borrowBook(Integer userId, BorrowBookRequest borrowBookRequest) {
        //先確認是有效user
        User user = userDao.getUserById(userId);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        for(BorrowBook borrowBook: borrowBookRequest.getBorrowBookList()){
            Inventory inventory = inventoryDao.getInventoryById(borrowBook.getInventoryId());

            //檢查是否有庫存，庫存書本狀態
            if(inventory == null){
                logger.warn("庫存ID {} 不存在", borrowBook.getInventoryId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (!inventory.getStatus().equals("在庫")) {
                logger.warn("庫存ID {} 無法出借", borrowBook.getInventoryId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //修改庫存狀態
            inventoryDao.updateStatus(borrowBook.getInventoryId(), "出借中");

            //新增借閱紀錄
            borrowingRecordDao.createRecord(userId, borrowBook.getInventoryId());
        }
    }

    @Transactional
    @Override
    public void returnBook(Integer userId, ReturnBookRequest returnBookRequest) {
        //先確認是有效user
        User user = userDao.getUserById(userId);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for(Integer recordId: returnBookRequest.getRecordIdList()){
            BorrowingRecord borrowingRecord = borrowingRecordDao.getRecordById(recordId);
            //檢查有沒有借閱紀錄
            if(borrowingRecord == null){
                logger.warn("借閱ID {} 不存在", recordId);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (borrowingRecord.getReturnTime() != null) {
                logger.warn("借閱ID {} 已歸還", recordId);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //修改庫存狀態
            inventoryDao.updateStatus(borrowingRecord.getInventoryId(), "在庫");

            //修改借閱紀錄
            borrowingRecordDao.updateRecord(recordId);
        }
    }
}
