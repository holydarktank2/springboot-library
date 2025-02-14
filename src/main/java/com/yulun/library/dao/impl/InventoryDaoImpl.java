package com.yulun.library.dao.impl;

import com.yulun.library.dao.InventoryDao;
import com.yulun.library.model.Inventory;
import com.yulun.library.rowmapper.InventoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InventoryDaoImpl implements InventoryDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Inventory getInventoryById(Integer inventoryId) {
        String sql = "SELECT inventory_id, isbn, status, store_time " +
                " FROM `inventory` WHERE inventory_id = :inventoryId";

        Map<String, Object> map = new HashMap<>();
        map.put("inventoryId", inventoryId);

        List<Inventory> inventoryList = namedParameterJdbcTemplate.query(sql, map, new InventoryRowMapper());

        if(inventoryList.size() > 0){
            return inventoryList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Inventory> getInventoriesByIsbn(String isbn) {
        String sql = "SELECT inventory_id, isbn, status, store_time " +
                " FROM `inventory` WHERE isbn = :isbn";

        Map<String, Object> map = new HashMap<>();
        map.put("isbn", isbn);

        List<Inventory> inventoryList = namedParameterJdbcTemplate.query(sql, map, new InventoryRowMapper());

        return inventoryList;
    }

    @Override
    public void updateStatus(Integer inventoryId, String status) {
        String sql = "UPDATE inventory SET status = :status " +
                " WHERE inventory_id = :inventoryId ";

        Map<String, Object> map = new HashMap<>();
        map.put("inventoryId" ,inventoryId);
        map.put("status", status);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
