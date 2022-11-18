package com.example.da1pet;

public class TestProducts {
    int img;
    String tensp;
    int gia;

    public TestProducts(int img, String tensp, int gia) {
        this.img = img;
        this.tensp = tensp;
        this.gia = gia;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
