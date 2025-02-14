package com.yulun.library.dto;

import jakarta.validation.constraints.NotBlank;

public class BorrowBook {
    @NotBlank
    private Integer inventoryId;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
}
