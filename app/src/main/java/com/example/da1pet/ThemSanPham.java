package com.example.da1pet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ThemSanPham extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        findViewById(R.id.btnback).setOnClickListener(v -> {
            onBackPressed();
        });
    }
}