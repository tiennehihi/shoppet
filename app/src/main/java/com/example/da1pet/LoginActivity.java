package com.example.da1pet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout ed_name, ed_pass;
    CheckBox chk_remember;
    Button btn_login, btn_register;
    SharedPreferences sharedPreferences;
    public void saveSharedPref(String usr, String pss) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", usr);
        editor.putString("pass", pss);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("user", "");
        String password = sharedPreferences.getString("pass", "");
        if(!userName.equals("")){
            ed_name.getEditText().setText(userName);
        }
        if(!password.equals("")){
            ed_pass.getEditText().setText(password);
            chk_remember.setChecked(true);
        }
        ed_name = findViewById(R.id.edUserName);
        ed_pass = findViewById(R.id.edPassWord);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btnLogin);
        btn_register = findViewById(R.id.btnRegister);
        findViewById(R.id.btnback).setOnClickListener(v -> {
            Intent intent = new Intent(this,LaucherActivity.class);
            startActivity(intent);

        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog =new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_dangky);
                Window window = dialog.getWindow();
                if(window == null) return;
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextInputLayout ed_ten = dialog.findViewById(R.id.ed_adm);
                TextInputLayout ed_pass = dialog.findViewById(R.id.ed_pass);

                Button btn_Save = dialog.findViewById(R.id.btn_register);
                btn_Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ed_ten.getEditText().getText().toString().equals("")){
                            ed_ten.setError("Vui lòng nhập tên");
                            return;
                        }else ed_ten.setError("");
                        if(ed_pass.getEditText().getText().toString().equals("")){
                            ed_pass.setError("Vui lòng nhập password");
                            return;
                        }else
                            ed_pass.setError("");





                            Toast.makeText(LoginActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.show();
            }
        });


    }
}