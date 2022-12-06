package com.example.da1pet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.da1pet.Model.Cart_item;
import com.example.da1pet.innerjoin.InnerCart;

import java.util.List;

@Dao
public interface Cart_itemDAO {
    @Insert
    void insertCartItem(Cart_item cartItem);
    @Update
    void updateCartItem(Cart_item cartItem);
    @Delete
    void DeleteCartItem(Cart_item cartItem);
    @Query("select * from Cart_item")
    List<Cart_item> getAll();
    @Query("delete  from cart_item where id_products = :id")
    void deleteitem(Integer id);
    @Query("select products.img_product,products.name_products,products.price,products.inventory,Cart_item.id_products as id_product " +
            "from Cart_item inner join products on Cart_item.id_products = products.id_products " +
            "where Cart_item.id_cart = :idcart")
    List<InnerCart> getAllCartItemById(String idcart);


}
