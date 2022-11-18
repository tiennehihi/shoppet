package com.example.da1pet.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Categorys.class, parentColumns = "id_category", childColumns = "id_category", onDelete = ForeignKey.CASCADE)})
public class Products {
    @PrimaryKey
    @NonNull
    private String id_products;
    private String id_category;
    private Integer inventory;
    private String name_products;

    public Products(String id_products, String id_category, Integer inventory, String name_products) {
        this.id_products = id_products;
        this.id_category = id_category;
        this.inventory = inventory;
        this.name_products = name_products;
    }

    public Products() {
    }

    public String getId_products() {
        return id_products;
    }

    public void setId_products(String id_products) {
        this.id_products = id_products;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getName_products() {
        return name_products;
    }

    public void setName_products(String name_products) {
        this.name_products = name_products;
    }
}
