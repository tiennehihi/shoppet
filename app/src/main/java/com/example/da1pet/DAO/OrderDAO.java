package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Query("select * from `Order`")
    List<Order> getAll();

    @Insert
    void insertTLoai(Order order);

    @Update
    void updateTLoai(Order order);

    @Delete
    void deleteTLoai(Order order);

    @Query("select * from `Order` where id_user = :iduser")
    List<Order> getAllByUser(String iduser);
}




