package com.example.da1pet.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.Order_detail;
import com.example.da1pet.innerjoin.InnerOrder;

import java.util.List;
@Dao
public interface OrderDetailDAO {
    @Query("select * from Order_detail")
    List<Order_detail> getAll();
    @Insert
    void insert(Order_detail order_detail);
    @Update
    void update(Order_detail order_detail);
    @Delete
    void delete(Order_detail order_detail);
    @Query("select * from order_detail where id_order = :id")
    List<Order_detail> getCount(Integer id);
    @Query("select products.name_products as nameproduct,products.price*order_detail.quantity as gia from order_detail inner join products on order_detail.id_products = products.id_products where id_order LIKE :id")
    List<InnerOrder> getOrderDetail(Integer id);
}
