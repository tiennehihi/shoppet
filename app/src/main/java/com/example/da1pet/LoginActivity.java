package com.example.da1pet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class LoginActivity extends AppCompatActivity {
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        findViewById(R.id.btnback).setOnClickListener(v -> {
            Intent intent = new Intent(this,LaucherActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btnRegister).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_dangky,null,false);
            builder.setView(view);
            alertDialog = builder.show();
            Window window = alertDialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view.findViewById(R.id.btnbacktologin).setOnClickListener(v1 -> {
                alertDialog.dismiss();
            });
        });
    }
}