package com.yulun.library.controller;

import com.yulun.library.model.Inventory;
import com.yulun.library.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventories/{isbn}")
    public ResponseEntity<List<Inventory>> getInventoriesByIsbn(@PathVariable String isbn){

        List<Inventory> inventoryList = inventoryService.getInventoriesByIsbn(isbn);

        return ResponseEntity.status(HttpStatus.OK).body(inventoryList);
    }
}
