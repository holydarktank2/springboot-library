package com.yulun.library.service.impl;

import com.yulun.library.dao.InventoryDao;
import com.yulun.library.model.Inventory;
import com.yulun.library.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao inventoryDao;
    @Override
    public List<Inventory> getInventoriesByIsbn(String isbn) {
        List<Inventory> inventoryList = inventoryDao.getInventoriesByIsbn(isbn);
        return inventoryList;
    }
}
