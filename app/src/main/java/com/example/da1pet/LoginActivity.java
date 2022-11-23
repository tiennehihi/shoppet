package com.example.da1pet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;


import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.User;

import com.example.da1pet.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    AlertDialog alertDialog;
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DbRoom db = DbRoom.getInstance(this);

        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        binding.edUserName.getEditText().setText(preferences.getString("USERNAME",""));
        binding.edPassWord.getEditText().setText(preferences.getString("PASSWORD",""));
        binding.chkRememberPass.setChecked(preferences.getBoolean("REMEMBER",false));

        findViewById(R.id.btnback).setOnClickListener(v -> {
            onBackPressed();
        });




        findViewById(R.id.btnRegister).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_dangky,null,false);
            builder.setView(view);
            alertDialog = builder.show();
            Window window = alertDialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            TextInputLayout txtIdUser, txtName, txtPhone, txtPassword;
            txtIdUser = view.findViewById(R.id.ed_idUser);
            txtName = view.findViewById(R.id.ed_Name);
            txtPhone = view.findViewById(R.id.ed_Number);
            txtPassword = view.findViewById(R.id.ed_pass);
            Button btnSave = view.findViewById(R.id.btn_register);
            //
            btnSave.setOnClickListener(v1 -> {
                String idUser = txtIdUser.getEditText().getText().toString();
                String name = txtName.getEditText().getText().toString();
                String phone = txtPhone.getEditText().getText().toString();
                String password = txtPassword.getEditText().getText().toString();
                if(checkValidate(idUser,name,phone,password)){
                    try {
                        Log.i("TAG", idUser + " " + name + " " + phone + " " + password);
                        db.userDAO().insert(new User(idUser,name,phone,password));
                        alertDialog.dismiss();
                        Toast.makeText(this, "DANG KY THANH CONG", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(this, "LOI INSERT DU LIEU!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "VUI LONG KHONG DE TRONG!", Toast.LENGTH_SHORT).show();
                }
            });
            view.findViewById(R.id.btnbacktologin).setOnClickListener(v1 -> {
                alertDialog.dismiss();
            });
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.edUserName.getEditText().getText().toString();
                String password = binding.edPassWord.getEditText().getText().toString();
                binding.edUserName.setError("");
                binding.edPassWord.setError("");
                if(username.isEmpty()){
                    binding.edUserName.setError("VUI LONG NHAP USERNAME");
                    return;
                }
                if(password.isEmpty()){
                    binding.edPassWord.setError("VUI LONG NHAP PASSWORD");
                    return;
                }
                User user = db.userDAO().checkLgin(username,password);
                if(user != null){
                    //remember
                    rememberUser(username,password,binding.chkRememberPass.isChecked());
                    //Start intent
                    Intent intent =new Intent(LoginActivity.this,NavigationActivity.class);
                    intent.putExtra("username", String.valueOf(user));
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "DANG NHAP THAT BAI", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checkValidate(String idUser, String name, String phone, String password) {
        return !(idUser.isEmpty() || name.isEmpty() || phone.isEmpty() || password.isEmpty());
    }

    private void rememberUser(String strUser, String strPass, boolean checked) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        if(checked){
            edit.putString("USERNAME",strUser);
            edit.putString("PASSWORD",strPass);
            edit.putBoolean("REMEMBER",true);
        }else{
            edit.clear();
        }
        edit.commit();
    }
}