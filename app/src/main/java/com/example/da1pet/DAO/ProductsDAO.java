package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.Products;

import java.util.List;
@Dao
public interface ProductsDAO {
    @Query("select * from Products")
    List<Products> getAll();
    @Insert
    void insert(Products products);
    @Update
    void update(Products products);
    @Delete
    void delete(Products products);
}
