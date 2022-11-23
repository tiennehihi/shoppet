package com.example.da1pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Categorys;
import com.example.da1pet.Model.Products;

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    DbRoom db;
    List<Products> list;
    String TAG = "zzzzzzzzzz";
    List<Categorys> categorysList;
    ImageView imageviewproduct;
    TextView tvtensp,tvloaisp,tvsoluongsanpham,tvmotasanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        db = DbRoom.getInstance(this);
        imageviewproduct = findViewById(R.id.imageviewproduct);
        tvtensp = findViewById(R.id.tvtensp);
        tvloaisp = findViewById(R.id.tvloaisp);
        tvsoluongsanpham = findViewById(R.id.tvsoluongsanpham);
        tvmotasanpham = findViewById(R.id.tvmotasanpham);
        findViewById(R.id.btnback).setOnClickListener(v -> {
            onBackPressed();
        });
        try {
            list = db.productsDAO().getItemById(getIntent().getStringExtra("idsanpham"));
            Products products = list.get(0);
            categorysList = db.categoryDAO().getItemById(products.getId_category());
            Categorys categorys = categorysList.get(0);

            Bitmap bitmap = BitmapFactory.decodeByteArray(products.getImg_product(),0,products.getImg_product().length);
            imageviewproduct.setImageBitmap(bitmap);
            tvtensp.setText(products.getName_products());
            tvloaisp.setText(categorys.getTenLoai());
            tvsoluongsanpham.setText(String.valueOf(products.getInventory()));
            tvmotasanpham.setText(products.getDescribe());
        }catch (Exception e){
            Log.e(TAG, "onCreate: "+ e.getMessage());
        }
    }
}