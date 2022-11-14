package com.example.da1pet.DbRoom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.da1pet.DAO.CategoryDAO;
import com.example.da1pet.DAO.OrderDAO;
import com.example.da1pet.DAO.OrderDetailDAO;
import com.example.da1pet.DAO.ProductsDAO;
import com.example.da1pet.DAO.UserDAO;
import com.example.da1pet.Model.categorys;
import com.example.da1pet.Model.order;
import com.example.da1pet.Model.order_detail;
import com.example.da1pet.Model.products;
import com.example.da1pet.Model.user;


@Database(entities = {categorys.class, order.class, products.class, order_detail.class, user.class}, exportSchema = false , version = 2)
public abstract class DbRoom extends RoomDatabase {
    private static final String DB_NAME = "DA1";
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
}
