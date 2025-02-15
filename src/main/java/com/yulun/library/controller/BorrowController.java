package com.yulun.library.controller;

import com.yulun.library.dto.BorrowBookRequest;
import com.yulun.library.dto.ReturnBookRequest;
import com.yulun.library.model.BorrowingRecord;
import com.yulun.library.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
public class BorrowController {

    @Autowired
    private BorrowBookService borrowBookService;

    @GetMapping("/users/{userId}/records")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BorrowingRecord>> getBorrowingRecords(@PathVariable Integer userId,
                                                                     //查詢條件
                                                                     @RequestParam(required = false) boolean isReturned,
                                                                     @AuthenticationPrincipal UserDetails userDetails
                                                                     ){

        List<BorrowingRecord> borrowingRecordList = borrowBookService.getRecords(userId);

        return ResponseEntity.status(HttpStatus.OK).body(borrowingRecordList);
    }
    @PostMapping("/users/{userId}/borrow")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> borrowBook(@PathVariable Integer userId,
                                        @RequestBody BorrowBookRequest borrowBookRequest,
                                        @AuthenticationPrincipal UserDetails userDetails){

        borrowBookService.borrowBook(userId, borrowBookRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/users/{userId}/return")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> returnBook(@PathVariable Integer userId,
                                        @RequestBody ReturnBookRequest returnBookRequest,
                                        @AuthenticationPrincipal UserDetails userDetails){

        borrowBookService.returnBook(userId, returnBookRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
