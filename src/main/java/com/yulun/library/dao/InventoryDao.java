package com.yulun.library.dao;

import com.yulun.library.model.Inventory;

import java.util.List;

public interface InventoryDao {
    List<Inventory> getInventoriesByIsbn(String isbn);
}
