package com.example.da1pet.innerjoin;

public class InnerCart {
    private byte[] img_product;
    private String name_products;
    private Integer price;
    private Integer inventory;
    private Integer id_product;

    public InnerCart(byte[] img_product, String name_products, Integer price, Integer inventory, Integer id_product) {
        this.img_product = img_product;
        this.name_products = name_products;
        this.price = price;
        this.inventory = inventory;
        this.id_product = id_product;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public byte[] getImg_product() {
        return img_product;
    }

    public void setImg_product(byte[] img_product) {
        this.img_product = img_product;
    }

    public String getName_products() {
        return name_products;
    }

    public void setName_products(String name_products) {
        this.name_products = name_products;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
