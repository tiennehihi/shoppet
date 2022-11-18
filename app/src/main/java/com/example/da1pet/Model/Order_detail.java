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
    @PrimaryKey
    @NonNull
    private String id_order;
    private String quantity;
    private String id_products;

    public Order_detail() {
    }

    public Order_detail(String id_order, String quantity, String id_products) {
        this.id_order = id_order;
        this.quantity = quantity;
        this.id_products = id_products;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getId_products() {
        return id_products;
    }

    public void setId_products(String id_products) {
        this.id_products = id_products;
    }

}
