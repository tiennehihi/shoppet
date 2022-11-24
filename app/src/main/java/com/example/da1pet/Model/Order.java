package com.example.da1pet.Model;

import androidx.annotation.NonNull;
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
    private Date date;

    public Order(String id_user, Integer total, Date date) {
        this.id_user = id_user;
        this.total = total;
        this.date = date;
    }



    public Order() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

