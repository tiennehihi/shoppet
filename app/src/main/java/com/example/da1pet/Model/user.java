package com.example.da1pet.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = {@ForeignKey(entity = order.class, parentColumns = "id_user", childColumns = "id_user", onDelete = ForeignKey.CASCADE)})
public class user {
    @PrimaryKey(autoGenerate = true)
    private String id_user;
    private String name;
    private Integer number;
    private String password;

    public user(String id_user, String name, Integer number, String password) {
        this.id_user = id_user;
        this.name = name;
        this.number = number;
        this.password = password;
    }

    public user() {
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
