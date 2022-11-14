package com.example.da1pet.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



@Entity(foreignKeys = {
        @ForeignKey(entity = products.class, parentColumns = "id_products", childColumns = "id_products", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = order.class, parentColumns = "id_order", childColumns = "id_order", onDelete = ForeignKey.CASCADE)
})

public class order_detail {
    @PrimaryKey
    @NonNull
    private String id_order;
    private String quantity;
    private String id_products;

    public order_detail() {
    }

    public order_detail(String id_order, String quantity, String id_products) {
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
