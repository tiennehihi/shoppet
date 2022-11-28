package com.example.da1pet.Model;

import androidx.room.Entity;

@Entity
public class Cart_item {

    private Integer id_cartItem;
    private Integer id_products;
    private Integer quantity;

    public Cart_item() {
    }

    public Cart_item(Integer id_cartItem, Integer id_products, Integer quantity) {

        this.id_cartItem = id_cartItem;
        this.id_products = id_products;
        this.quantity = quantity;
    }

    public Integer getId_cartItem() {
        return id_cartItem;
    }

    public void setId_cartItem(Integer id_cartItem) {
        this.id_cartItem = id_cartItem;
    }

    public Integer getId_products() {
        return id_products;
    }

    public void setId_products(Integer id_products) {
        this.id_products = id_products;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
