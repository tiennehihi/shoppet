package com.example.da1pet.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = products.class, parentColumns = "id_product", childColumns = "id_product", onDelete = ForeignKey.CASCADE)})
public class categorys {
    @PrimaryKey(autoGenerate = true)
    private String id_category;
    private String name;
    private String note;
    private Integer price;

    public categorys(String id_category, String name, String note, Integer price) {
        this.id_category = id_category;
        this.name = name;
        this.note = note;
        this.price = price;
    }

    public categorys() {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
