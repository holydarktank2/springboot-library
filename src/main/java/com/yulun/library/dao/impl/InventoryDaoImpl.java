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
    public List<Inventory> getInventoriesByIsbn(String isbn) {
        String sql = "SELECT inventory_id, isbn, status, store_time " +
                " FROM `inventory` WHERE isbn = :isbn";

        Map<String, Object> map = new HashMap<>();
        map.put("isbn", isbn);

        System.out.println("sql: " + sql);

        List<Inventory> inventoryList = namedParameterJdbcTemplate.query(sql, map, new InventoryRowMapper());

        System.out.println(inventoryList.size());
        for(int i=0;i<inventoryList.size();i++){
            System.out.println(inventoryList.get(i));
        }

        return inventoryList;
    }
}
