package com.example.da1pet.innerjoin;

public class InnerOrder {
    public String nameproduct;
    public Integer gia;

    public InnerOrder(String nameproduct, Integer gia) {
        this.nameproduct = nameproduct;
        this.gia = gia;
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
