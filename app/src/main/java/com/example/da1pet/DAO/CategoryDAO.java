package com.example.da1pet.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.Categorys;

import java.util.List;

@Dao
public interface CategoryDAO {
      @Query("select * from Categorys")
      List<Categorys> getAll();
      @Insert
      void insertTLoai(Categorys categorys);
      @Update
      void updateTLoai(Categorys categorys);
      @Delete
      void deleteTLoai(Categorys categorys);
      @Query("select *from Categorys where Categorys.id_category = :maLoai limit 1")
      Categorys getCategoryByCode(int maLoai);
      @Query("select *from Categorys where Categorys.tenLoai = :tenLoai limit 1")
      Categorys getCategoryByName(String tenLoai);
  }


