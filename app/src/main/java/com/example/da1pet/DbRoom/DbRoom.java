package com.example.da1pet.DbRoom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.da1pet.DAO.CartDAO;
import com.example.da1pet.DAO.Cart_itemDAO;
import com.example.da1pet.DAO.CategoryDAO;
import com.example.da1pet.DAO.OrderDAO;
import com.example.da1pet.DAO.OrderDetailDAO;
import com.example.da1pet.DAO.ProductsDAO;
import com.example.da1pet.DAO.UserDAO;
import com.example.da1pet.Model.Cart;
import com.example.da1pet.Model.Cart_item;
import com.example.da1pet.Model.Categorys;
import com.example.da1pet.Model.Order_detail;
import com.example.da1pet.Model.User;
import com.example.da1pet.Model.Order;
import com.example.da1pet.Model.Products;


@Database(entities = {Categorys.class, Order.class, Products.class, Order_detail.class, User.class, Cart.class, Cart_item.class}, exportSchema = false , version = 2)
public abstract class DbRoom extends RoomDatabase {
    private static final String DB_NAME = "DA1.db";
    private static DbRoom instance;
    public static synchronized DbRoom getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DbRoom.class,DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract ProductsDAO productsDAO();
    public abstract UserDAO userDAO();
    public abstract OrderDAO orderDAO();
    public abstract OrderDetailDAO orderDetailDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract CartDAO cartDAO();
    public abstract Cart_itemDAO cartItemDAO();
}
