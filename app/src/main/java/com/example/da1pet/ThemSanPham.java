package com.example.da1pet;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.Toast;

;

import java.util.List;


public class ThemSanPham extends AppCompatActivity {
    int SELECT_IMAGE_CODE = 1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        findViewById(R.id.btnback).setOnClickListener(v -> {
            onBackPressed();
        });
        img = findViewById(R.id.imgpro);
        findViewById(R.id.btnpicpr).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"title"),SELECT_IMAGE_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            try {
                Uri uri = data.getData();
                img.setImageURI(uri);
            }catch (Exception e){
                e.getMessage();
            }

        }
    }
}