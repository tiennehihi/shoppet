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
    void insert(Order order);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);
    @Query("select id_order from `order` as hd where hd.id_order = :id_order and hd.id_user = :id_user order by id_order desc limit 1")
    int getCode(int id_order, String id_user);

    @Query("select * from `Order` where id_user = :iduser")
    List<Order> getAllByUser(String iduser);
}




