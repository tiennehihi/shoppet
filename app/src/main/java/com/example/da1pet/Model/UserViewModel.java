package com.example.da1pet.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<User> user = new MutableLiveData<>();

    public void setUser(User user){
        this.user.setValue(user);
    }

    public LiveData getUser(){
        return this.user;
    }
}