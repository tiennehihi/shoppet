package com.example.da1pet.innerjoin;

public class InnerDonHang {
    private String id_user;
    private Integer id_order;
    private String name;
    private Integer total;
    private Integer thanhToan;

    public InnerDonHang(String id_user, Integer id_order, String name, Integer total, Integer thanhToan) {
        this.id_user = id_user;
        this.id_order = id_order;
        this.name = name;
        this.total = total;
        this.thanhToan = thanhToan;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(Integer thanhToan) {
        this.thanhToan = thanhToan;
    }
}
