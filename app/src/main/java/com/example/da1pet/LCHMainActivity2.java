package com.example.da1pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Categorys;
import com.example.da1pet.Model.User;

public class LCHMainActivity2 extends AppCompatActivity {
    ProgressBar p;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lchmain2);
        p = findViewById(R.id.progress);
        p.setMax(110);
        p.setProgress(0);

        new Thread(){
            @Override
            public void run() {
                while (p.getProgress()<=p.getMax()){
                    try{
                        Thread.sleep(150);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    p.incrementProgressBy(4);
                }
            }
        }.start();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DbRoom db = DbRoom.getInstance(LCHMainActivity2.this);
                if(db.userDAO().getAll().size() == 0){
                    db.userDAO().insert(new User("admin","Đinh Thành Huân","0799187604","123"));
                }
                Intent intent = new Intent(LCHMainActivity2.this, NavigationActivity.class);
                startActivity(intent);
                
            }
        }, 2000);
    }
}