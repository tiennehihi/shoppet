package com.example.da1pet.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categorys {
    @PrimaryKey(autoGenerate = true)
    private Integer id_category;
    private String tenLoai;

    public Categorys() {
    }

    public Categorys(String tenLoai) {
        this.tenLoai = tenLoai;
    }


    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
