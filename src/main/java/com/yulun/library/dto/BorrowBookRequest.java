package com.yulun.library.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BorrowBookRequest {
    @NotEmpty
    private List<BorrowBook> borrowBookList;

    public List<BorrowBook> getBorrowBookList() {
        return borrowBookList;
    }

    public void setBorrowBookList(List<BorrowBook> borrowBookList) {
        this.borrowBookList = borrowBookList;
    }
}
