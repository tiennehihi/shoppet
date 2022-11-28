package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.da1pet.Model.Cart;


import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insertCart(Cart cart);
    @Update
    void updateCart(Cart cart);
    @Delete
    void deleteCart(Cart cart);
    @Query("select * from Cart")
    List<Cart> getAll();
}
