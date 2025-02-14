package com.yulun.library.service;

import com.yulun.library.dto.BorrowBookRequest;
import com.yulun.library.dto.ReturnBookRequest;
import com.yulun.library.model.BorrowingRecord;

import java.util.List;

public interface BorrowBookService {

    List<BorrowingRecord> getRecords(Integer userId);
    void borrowBook(Integer userId, BorrowBookRequest borrowBookRequest);
    void returnBook(Integer userId, ReturnBookRequest returnBookRequest);
}
