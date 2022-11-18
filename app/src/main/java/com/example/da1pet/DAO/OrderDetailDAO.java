package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.Order_detail;

import java.util.List;
@Dao
public interface OrderDetailDAO {
    @Query("select * from Order_detail")
    List<Order_detail> getAll();
    @Insert
    void insert(Order_detail order_detail);
    @Update
    void update(Order_detail order_detail);
    @Delete
    void delete(Order_detail order_detail);
}
