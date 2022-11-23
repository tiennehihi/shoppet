package com.example.da1pet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
    AlertDialog alertDialog;
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
            Bundle bundle = getIntent().getExtras();
            list = db.productsDAO().getItemById(bundle.getInt("idsanpham"));
            Products products = list.get(0);
            categorysList = db.categoryDAO().getItemById(products.getId_category());
            Categorys categorys = categorysList.get(0);

            Bitmap bitmap = BitmapFactory.decodeByteArray(products.getImg_product(),0,products.getImg_product().length);
            imageviewproduct.setImageBitmap(bitmap);
            tvtensp.setText(products.getName_products());
            tvloaisp.setText(categorys.getTenLoai());
            tvsoluongsanpham.setText(String.valueOf(products.getInventory()));
            tvmotasanpham.setText(products.getDescribe());
            findViewById(R.id.btnbuypr).setOnClickListener(v -> {
                AlertDialog.Builder  builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.dialog_buy,null,false);
                builder.setView(view);
                alertDialog = builder.show();
                Window window = alertDialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                WindowManager.LayoutParams windowManager = window.getAttributes();
                windowManager.gravity = Gravity.BOTTOM;
                window.setAttributes(windowManager);
                ImageView imgpr = view.findViewById(R.id.imgpr);
                imgpr.setImageBitmap(bitmap);
                TextView tvkho = view.findViewById(R.id.tvkho);
                tvkho.setText(String.valueOf(products.getInventory()));
                TextView tvgia = view.findViewById(R.id.tvgiapr);
                tvgia.setText(String.valueOf(products.getPrice()));
                Button tbtn = view.findViewById(R.id.tbtn);
                Button gbtn = view.findViewById(R.id.gbtn);
                TextView tvsoluong = view.findViewById(R.id.tvsoluoong);
                tbtn.setOnClickListener(v1 -> {
                    tvsoluong.setText(String.valueOf(Integer.parseInt(tvsoluong.getText().toString())+1));
                    if (Integer.parseInt(tvsoluong.getText().toString())==1){
                        gbtn.setEnabled(false);
                    }else {
                        gbtn.setEnabled(true);
                        gbtn.setOnClickListener(v2 -> {
                            tvsoluong.setText(String.valueOf(Integer.parseInt(tvsoluong.getText().toString())-1));
                            if (Integer.parseInt(tvsoluong.getText().toString())==1){
                                gbtn.setEnabled(false);
                            }
                        });
                    }
                });
            });
        }catch (Exception e){
            Log.e(TAG, "onCreate: "+ e.getMessage());
        }
    }
}