package com.example.da1pet.Model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(foreignKeys = {
        @ForeignKey(entity = Categorys.class, parentColumns = "id_category", childColumns = "id_category", onDelete = ForeignKey.CASCADE)
})
public class Products {
    @PrimaryKey(autoGenerate = true)
    private Integer id_products;
    private Integer id_category;
    private Integer inventory;
    private String name_products;
    private Integer price;
    private String describe;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] img_product;

    public Products(Integer id_category, Integer inventory, String name_products, Integer price, String describe, byte[] img_product) {
        this.id_category = id_category;
        this.inventory = inventory;
        this.name_products = name_products;
        this.price = price;
        this.describe = describe;
        this.img_product = img_product;
    }

    public Products(Integer id_products, Integer id_category, Integer inventory, String name_products, Integer price, String describe, byte[] img_product) {
        this.id_products = id_products;
        this.id_category = id_category;
        this.inventory = inventory;
        this.name_products = name_products;
        this.price = price;
        this.describe = describe;
        this.img_product = img_product;
    }

    public Products() {
    }

    public byte[] getImg_product() {
        return img_product;
    }

    public void setImg_product(byte[] img_product) {
        this.img_product = img_product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    public Integer getId_products() {
        return id_products;
    }

    public void setId_products(Integer id_products) {
        this.id_products = id_products;
    }

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
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
