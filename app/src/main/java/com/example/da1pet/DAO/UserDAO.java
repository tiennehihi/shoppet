package com.example.da1pet.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.user;

import java.util.List;

public interface UserDAO {
    @Query("select * from user")
    List<user> getAll();
    @Insert
    void insert(user user);
    @Update
    void update(user user);
    @Delete
    void delete(user user);
}
