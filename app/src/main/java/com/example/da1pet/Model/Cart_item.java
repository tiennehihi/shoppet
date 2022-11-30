package com.example.da1pet.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Products.class, parentColumns = "id_products", childColumns = "id_products", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Cart.class, parentColumns = "id_cart", childColumns = "id_cart", onDelete = ForeignKey.CASCADE)
})
public class Cart_item {
    @PrimaryKey(autoGenerate = true)
    private Integer id_cartItem;
    private String id_cart;
    private Integer id_products;
    private Integer quantity;

    public Cart_item() {
    }

    public Cart_item( String id_cart, Integer id_products, Integer quantity) {
        this.id_cart = id_cart;
        this.id_products = id_products;
        this.quantity = quantity;
    }

    public Integer getId_cartItem() {
        return id_cartItem;
    }

    public void setId_cartItem(Integer id_cartItem) {
        this.id_cartItem = id_cartItem;
    }

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
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
