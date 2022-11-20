package com.example.da1pet.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categorys {
    @PrimaryKey
    @NonNull
    private String id_category;
    private String name;


    public Categorys(String id_category, String name) {
        this.id_category = id_category;
        this.name = name;
    }

    public Categorys() {
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
