package com.yulun.library.service;

import com.yulun.library.model.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> getInventoriesByIsbn(String isbn);
}
