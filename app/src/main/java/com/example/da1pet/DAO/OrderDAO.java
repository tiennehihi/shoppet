package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.Order;
import com.example.da1pet.innerjoin.InnerDonHang;

import java.util.List;

@Dao
public interface OrderDAO {
    @Query("select * from `Order`")
    List<Order> getAll();

    @Insert
    void insert(Order order);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("select id_order from `order` as hd where hd.id_order = :id_order and hd.id_user = :id_user order by id_order desc limit 1")
    int getCode(int id_order, String id_user);

    @Query("select * from `Order` where id_user = :iduser order by id_order desc")
    List<Order> getAllByUser(String iduser);

    @Query("update `order` set status = :newstatus where id_order = :id")
    void updateStatus(String newstatus,int id);

    @Query("select status from `order` where id_order = :id")
    List<String> getstatus(int id);

    @Query("select user.id_user as id_user, `order`.id_order as id_order , user.name as name, `order`.total as total,`order`.thanhToan as thanhToan from `order` inner join user on user.id_user = `order`.id_user where `order`.status = :s")
    List<InnerDonHang> getByStatus(String s);

    @Query("select * from `order` where id_order = :id")
    List<Order> getAllById(int id);
}




