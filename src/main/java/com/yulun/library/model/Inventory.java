package com.yulun.library.model;

import java.util.Date;

public class Inventory {
    Integer inventorId;
    String isbn;
    String status;
    Date storeTime;

    public Integer getInventorId() {
        return inventorId;
    }

    public void setInventorId(Integer inventorId) {
        this.inventorId = inventorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }
}
