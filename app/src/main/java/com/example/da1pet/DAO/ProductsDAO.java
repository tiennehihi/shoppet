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

    @Query("update products set inventory = :newinven where id_products = :id")
    void updateinven(Integer id, Integer newinven);

    @Query("select * from Products WHERE id_products = :id")
    List<Products> getItemById(int id);

    @Query("delete from products where id_products = :id")
    void deleteproduct(Integer id);

    @Query("select*from products where name_products like :keyWord")
    List<Products> getSearch(String keyWord);
}
