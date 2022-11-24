package com.example.da1pet.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



@Entity(foreignKeys = {
        @ForeignKey(entity = Products.class, parentColumns = "id_products", childColumns = "id_products", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Order.class, parentColumns = "id_order", childColumns = "id_order", onDelete = ForeignKey.CASCADE)
})

public class Order_detail {
    @PrimaryKey(autoGenerate = true)
    private Integer id_order_detail;
    private Integer id_order;
    private Integer id_products;
    private Integer quantity;


    public Order_detail(Integer id_order, Integer id_products, Integer quantity) {
        this.id_order = id_order;
        this.id_products = id_products;
        this.quantity = quantity;
    }

    public Integer getId_order_detail() {
        return id_order_detail;
    }

    public void setId_order_detail(Integer id_order_detail) {
        this.id_order_detail = id_order_detail;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId_products() {
        return id_products;
    }

    public void setId_products(Integer id_products) {
        this.id_products = id_products;
    }
}
