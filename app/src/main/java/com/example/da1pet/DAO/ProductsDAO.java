package com.example.da1pet.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.products;

import java.util.List;

public interface ProductsDAO {
    @Query("select * from products")
    List<products> getAll();
    @Insert
    void insert(products products);
    @Update
    void update(products products);
    @Delete
    void delete(products products);
}
