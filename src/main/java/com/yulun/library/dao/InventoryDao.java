package com.yulun.library.dao;

import com.yulun.library.model.Inventory;

import java.util.List;

public interface InventoryDao {

    Inventory getInventoryById(Integer inventoryId);
    List<Inventory> getInventoriesByIsbn(String isbn);
    void updateStatus(Integer inventoryId, String status);

}
