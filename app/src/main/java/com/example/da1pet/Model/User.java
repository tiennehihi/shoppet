package com.example.da1pet.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String id_user;
    private String name;
    private String number;
    private String password;


    public User(String name) {
        this.name = name;
    }
    public User(@NonNull String id_user, String name, String number, String password) {
        this.id_user = id_user;
        this.name = name;
        this.number = number;
        this.password = password;
    }

    public User() {
    }

    @NonNull
    public String getId_user() {
        return id_user;
    }

    public void setId_user(@NonNull String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
