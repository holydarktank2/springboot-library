package com.yulun.library.rowmapper;

import com.yulun.library.model.Book;
import com.yulun.library.model.Inventory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setInventorId(rs.getInt("inventory_id"));
        inventory.setIsbn(rs.getString("isbn"));
        inventory.setStatus(rs.getString("status"));
        inventory.setStoreTime(rs.getTimestamp("store_time"));

        return inventory;
    }
}
