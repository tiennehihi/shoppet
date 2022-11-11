package com.example.da1pet.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.order_detail;

import java.util.List;

public interface OrderDetailDAO {
    @Query("select * from order_detail")
    List<order_detail> getAll();
    @Insert
    void insert(order_detail order_detail);
    @Update
    void update(order_detail order_detail);
    @Delete
    void delete(order_detail order_detail);
}
