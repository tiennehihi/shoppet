package com.example.da1pet.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.da1pet.Dateconverter;

import java.util.Date;


@Entity(foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id_user", childColumns = "id_user", onDelete = ForeignKey.CASCADE)})
public class Order {
    @PrimaryKey(autoGenerate = true)
    private Integer id_order;
    private String id_user;
    private Integer total;
    @TypeConverters({Dateconverter.class})
    private String date;
    private Integer thanhToan;

    public Order(String username, int i, Date currentTime) {
    }

    public Order() {
        this.id_order = id_order;
        this.id_user = id_user;
        this.total = total;
        this.date = date;
        this.thanhToan = thanhToan;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(Integer thanhToan) {
        this.thanhToan = thanhToan;
    }
}

