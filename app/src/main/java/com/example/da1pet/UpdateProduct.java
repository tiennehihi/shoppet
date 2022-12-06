package com.example.da1pet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Categorys;
import com.example.da1pet.Model.Products;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UpdateProduct extends AppCompatActivity {
    DbRoom db;
    ArrayList<Categorys> listcategory;
    Spinner spinner;
    EditText edtidsanpham;
    TextInputLayout edttensp, edtinventory, edtprice, edtmota;
    ImageView img;
    int SELECT_IMAGE_CODE = 1;
    String id = "";
    Button btnsua;
    Button btnsuaanh;
    ArrayList<Products> listproduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        findViewById(R.id.btnback).setOnClickListener(v -> {
            onBackPressed();
        });
        db = DbRoom.getInstance(this);
        listcategory = (ArrayList<Categorys>) db.categoryDAO().getAll();
        ArrayList<String> listspn = new ArrayList<>();
        for (int i = 0; i < listcategory.size(); i++) {
            Categorys categorys = listcategory.get(i);
            listspn.add(categorys.getTenLoai());
        }
        Bundle bundle = getIntent().getExtras();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listspn);
        spinner = findViewById(R.id.spntheloai);
        spinner.setAdapter(adapter);
        edtidsanpham = findViewById(R.id.edtidsanpham);
        edttensp = findViewById(R.id.edttensp);
        spinner.setEnabled(true);
        edtinventory = findViewById(R.id.edtinventory);
        edtprice = findViewById(R.id.edtprice);
        edtmota = findViewById(R.id.edtmota);
        img = findViewById(R.id.imgproduct);
        listproduct = (ArrayList<Products>) db.productsDAO().getItemById(bundle.getInt("idpr"));
        edttensp.setEnabled(true);
        edtinventory.setEnabled(true);
        edtprice.setEnabled(true);
        edtmota.setEnabled(true);
        Products products = listproduct.get(0);
        edttensp.getEditText().setText(products.getName_products());
        edtinventory.getEditText().setText(String.valueOf(products.getInventory()));
        edtprice.getEditText().setText(String.valueOf(products.getPrice()));
        edtmota.getEditText().setText(products.getDescribe());
        spinner.setSelection(products.getId_category() - 1);
        Bitmap bitmap = BitmapFactory.decodeByteArray(products.getImg_product(), 0, products.getImg_product().length);
        img.setImageBitmap(bitmap);
        findViewById(R.id.suaimg).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"title"),SELECT_IMAGE_CODE);
        });
        btnsua = findViewById(R.id.btnsua);
        btnsua.setOnClickListener(v1 -> {
            try {
                if (edttensp.getEditText().getText().toString().equals("") || edtinventory.getEditText().getText().toString().equals("") || edtprice.getEditText().getText().toString().equals("") || edtmota.getEditText().getText().toString().equals("")) {
                    Toast.makeText(this, "Không dược để trống", Toast.LENGTH_SHORT).show();
                } else {
                    db.productsDAO().update(new Products(bundle.getInt("idpr"), spinner.getSelectedItemPosition() + 1
                            , Integer.parseInt(edtinventory.getEditText().getText().toString())
                            , edttensp.getEditText().getText().toString()
                            , Integer.parseInt(edtprice.getEditText().getText().toString())
                            , edtmota.getEditText().getText().toString()
                            , products.getImg_product())
                    );
                    Toast.makeText(this, "Sửa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, NavigationActivity.class);
                    intent.putExtra("username", getIntent().getStringExtra("username"));
                    startActivity(intent);
                }

            } catch (Exception e) {
                e.getMessage();
            }
        });
        findViewById(R.id.btnxoa).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xóa");
            builder.setMessage("Bạn có muốn xóa không?");
            builder.setNegativeButton("Kkông", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.productsDAO().deleteproduct(Integer.parseInt(id));
                    Toast.makeText(UpdateProduct.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateProduct.this, NavigationActivity.class);
                    intent.putExtra("username", getIntent().getStringExtra("username"));
                    startActivity(intent);
                }
            });
            builder.show();
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            try {
                Bundle bundle = getIntent().getExtras();
                Uri uri = data.getData();
                img.setImageURI(uri);
                Bitmap imgproduct = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                byte[] imginbyte;
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                imgproduct.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                imginbyte = outputStream.toByteArray();
                btnsua.setOnClickListener(v -> {
                    if (edttensp.getEditText().getText().toString().equals("") || edtinventory.getEditText().getText().toString().equals("") || edtprice.getEditText().getText().toString().equals("") || edtmota.getEditText().getText().toString().equals("")) {
                        Toast.makeText(this, "Không dược để trống", Toast.LENGTH_SHORT).show();
                    } else {
                        db.productsDAO().update(new Products(bundle.getInt("idpr"), spinner.getSelectedItemPosition() + 1
                                , Integer.parseInt(edtinventory.getEditText().getText().toString())
                                , edttensp.getEditText().getText().toString()
                                , Integer.parseInt(edtprice.getEditText().getText().toString())
                                , edtmota.getEditText().getText().toString()
                                , imginbyte)
                        );
                        Toast.makeText(this, "Sửa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, NavigationActivity.class);
                        intent.putExtra("username", getIntent().getStringExtra("username"));
                        startActivity(intent);
                    }
                });
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}