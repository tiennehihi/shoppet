package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.order;

import java.util.List;

@Dao
public interface OrderDAO {
        @Query("select * from `order`")
        List<order> getAll();
        @Insert
        void insertTLoai(order order);
        @Update
        void updateTLoai(order order);
        @Delete
        void deleteTLoai(order order);

    }




