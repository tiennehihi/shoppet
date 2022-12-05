package com.example.da1pet.innerjoin;

public class InnerThongTInDonHang {
    private byte[] img;
    private String name;
    private Integer soluong;
    private Integer price;

    public InnerThongTInDonHang(byte[] img, String name, Integer soluong, Integer price) {
        this.img = img;
        this.name = name;
        this.soluong = soluong;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }
}
