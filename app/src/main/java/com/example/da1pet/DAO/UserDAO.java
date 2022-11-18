package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.User;

import java.util.List;
@Dao
public interface UserDAO {

    @Insert
    void insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);
    @Query("select * from User")
    List<User> getAll();
    @Query("select *from User where id_user = :username and password= :password limit 1")
    User checkLgin(String username, String password);
    @Query("select *from User where id_user = :username limit 1")
    User getUser(String username);

}
