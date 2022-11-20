package com.example.da1pet;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Categorys;
import com.example.da1pet.Model.Products;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ThemSanPham extends AppCompatActivity {
    int SELECT_IMAGE_CODE = 1;
    ImageView img;
    DbRoom db;
    Spinner spinner;
    EditText edtid,edtname,edtinventory,edtprice,edtdescap;
    String TAG = "zzzzzzz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        db = DbRoom.getInstance(this);

        edtid = findViewById(R.id.edt_id);
        edtname = findViewById(R.id.edt_tensp);
        edtinventory = findViewById(R.id.edt_inventory);
        ArrayList<Categorys> list = new ArrayList<>();
        list = (ArrayList<Categorys>) db.categoryDAO().getAll();
        ArrayList<String> listspn = new ArrayList<>();
        for (int i= 0;i<list.size();i++){
            Categorys categorys = list.get(i);
            listspn.add(categorys.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listspn);
         spinner = findViewById(R.id.spntheloai);
         spinner.setAdapter(adapter);
        edtprice = findViewById(R.id.edtgia);
        edtdescap = findViewById(R.id.edtmota);
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

            findViewById(R.id.btnthemsanpham).setOnClickListener(v -> {
                if (edtid.getText().toString() == ""|| edtname.getText().toString() == ""|| edtinventory.getText().toString() == ""|| edtprice.getText().toString() == ""|| edtdescap.getText().toString() == ""){
                    Toast.makeText(this, "Không được để trống trường nào", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Hãy chọn ảnh sản phẩm", Toast.LENGTH_LONG).show();
                }

            });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            try {
                Uri uri = data.getData();
                img.setImageURI(uri);
                Bitmap imgproduct = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                byte[] imginbyte;
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                imgproduct.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                imginbyte = outputStream.toByteArray();

                findViewById(R.id.btnthemsanpham).setOnClickListener(v -> {
                    if (edtid.getText().toString() == ""|| edtname.getText().toString() == ""|| edtinventory.getText().toString() == ""||  edtprice.getText().toString() == ""|| edtdescap.getText().toString() == ""){
                        Toast.makeText(this, "Không được để trống trường nào", Toast.LENGTH_LONG).show();
                    }else {
                        try {
                            db.productsDAO().insert(new Products(edtid.getText().toString(),String.valueOf(spinner.getSelectedItemPosition()+1),Integer.parseInt(edtinventory.getText().toString()),edtname.getText().toString(),Integer.parseInt(edtprice.getText().toString()),edtdescap.getText().toString(),imginbyte));
                            Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Log.d(TAG, e.getMessage());
                        }
                    }
                });
            }catch (Exception e){
                e.getMessage();
            }

        }
    }
}