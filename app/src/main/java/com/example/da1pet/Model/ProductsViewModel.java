package com.example.da1pet.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ProductsViewModel extends ViewModel {
    private final MutableLiveData<List<Products>> listProduct = new MutableLiveData<>();

    public void setListProduct(List<Products> listProduct){
        this.listProduct.setValue(listProduct);
    }

    public LiveData getListProduct(){
        return this.listProduct;
    }
}
