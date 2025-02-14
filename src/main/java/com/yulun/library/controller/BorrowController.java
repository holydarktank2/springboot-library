package com.yulun.library.controller;

import com.yulun.library.dto.BorrowBookRequest;
import com.yulun.library.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {

    @Autowired
    private BorrowBookService borrowBookService;

    @PostMapping("/users/{userId}/borrow")
    public ResponseEntity<?> borrowBook(@PathVariable Integer userId,
            @RequestBody BorrowBookRequest borrowBookRequest){

        borrowBookService.borrowBook(userId, borrowBookRequest);
        return null;
    }
}
