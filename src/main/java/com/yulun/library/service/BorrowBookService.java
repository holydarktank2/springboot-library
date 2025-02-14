package com.yulun.library.service;

import com.yulun.library.dto.BorrowBookRequest;

public interface BorrowBookService {
    void borrowBook(Integer userId, BorrowBookRequest borrowBookRequest);
}
