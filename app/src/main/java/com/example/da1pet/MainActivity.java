package com.example.da1pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Categorys;

public class MainActivity extends AppCompatActivity {
    ProgressBar p;
    Handler handler;
    DbRoom db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run() {
                while (p.getProgress()<=p.getMax()){
                    try{
                        Thread.sleep(150);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    p.incrementProgressBy(5);
                }
            }
        }.start();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                db.categoryDAO().insertTLoai(new Categorys(1+"","Vật nuôi"));
                db.categoryDAO().insertTLoai(new Categorys(2+"","Thức ăn"));
                db.categoryDAO().insertTLoai(new Categorys(3+"","Phụ kiện"));
            }
        }, 3000);
    }
}