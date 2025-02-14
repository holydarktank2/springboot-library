package com.yulun.library.controller;

import com.yulun.library.dto.BorrowBookRequest;
import com.yulun.library.dto.ReturnBookRequest;
import com.yulun.library.model.BorrowingRecord;
import com.yulun.library.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowController {

    @Autowired
    private BorrowBookService borrowBookService;

    @GetMapping("/users/{userId}/records")
    public ResponseEntity<List<BorrowingRecord>> getBorrowingRecords(@PathVariable Integer userId,
            //查詢條件
            @RequestParam(required = false) boolean isReturned
            ){

        List<BorrowingRecord> borrowingRecordList = borrowBookService.getRecords(userId);

        return ResponseEntity.status(HttpStatus.OK).body(borrowingRecordList);
    }
    @PostMapping("/users/{userId}/borrow")
    public ResponseEntity<?> borrowBook(@PathVariable Integer userId,
            @RequestBody BorrowBookRequest borrowBookRequest){

        borrowBookService.borrowBook(userId, borrowBookRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/users/{userId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Integer userId,
                                        @RequestBody ReturnBookRequest returnBookRequest){

        borrowBookService.returnBook(userId, returnBookRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
