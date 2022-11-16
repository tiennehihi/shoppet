package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.user;

import java.util.List;
@Dao
public interface UserDAO {

    @Insert
    void insert(user user);
    @Update
    void update(user user);
    @Delete
    void delete(user user);
    @Query("select * from user")
    List<user> getAll();
    @Query("select *from user where id_user = :username and password= :password limit 1")
    user checkLogin(String username, String password);
    @Query("select *from user where id_user = :username limit 1")
    user getUserByCode(String username);

}
