package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.Cart_item;

import java.util.List;

@Dao
public interface Cart_itemDAO {
    @Insert
    void insertCartItem(Cart_item cartItem);
    @Update
    void updateCartItem(Cart_item cartItem);
    @Delete
    void DeleteCartItem(Cart_item cartItem);
    @Query("select * from Cart_item")
    List<Cart_item> getAll();

}
