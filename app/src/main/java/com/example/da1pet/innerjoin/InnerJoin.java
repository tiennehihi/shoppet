package com.example.da1pet.innerjoin;

public class InnerJoin {
    public String nameproduct;
    public Integer quantity;
    public Integer gia;

    public InnerJoin(String nameproduct, Integer quantity, Integer gia) {
        this.nameproduct = nameproduct;
        this.quantity = quantity;
        this.gia = gia;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }
}
