package com.example.da1pet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import java.util.ArrayList;

public class DeleteProduct extends AppCompatActivity {
    DbRoom db;
    ArrayList<Categorys> listcategory;
    Spinner spinner;
    EditText edtidsanpham;
    TextInputLayout edttensp, edtinventory, edtprice, edtmota;
    ImageView img;
    String id = "";
    Button btnxoa;
    ArrayList<Products> listproduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);
        db = DbRoom.getInstance(this);
        listcategory = (ArrayList<Categorys>) db.categoryDAO().getAll();
        ArrayList<String> listspn = new ArrayList<>();
        findViewById(R.id.btnback).setOnClickListener(v -> {
            onBackPressed();
        });
        for (int i = 0; i < listcategory.size(); i++) {
            Categorys categorys = listcategory.get(i);
            listspn.add(categorys.getTenLoai());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listspn);
        spinner = findViewById(R.id.spntheloai);
        spinner.setAdapter(adapter);
        edtidsanpham = findViewById(R.id.edtidsanpham);
        edttensp = findViewById(R.id.edttensp);
        edtinventory = findViewById(R.id.edtinventory);
        edtprice = findViewById(R.id.edtprice);
        edtmota = findViewById(R.id.edtmota);
        img = findViewById(R.id.imgproduct);
        findViewById(R.id.btntimkiem).setOnClickListener(v -> {
            if (edtidsanpham.getText().toString().equals("")) {
                Toast.makeText(this, "Hãy nhập ID sản phẩm muốn sửa", Toast.LENGTH_SHORT).show();
            } else {
                listproduct = (ArrayList<Products>) db.productsDAO().getItemById(Integer.parseInt(edtidsanpham.getText().toString()));
                if (listproduct.size() == 0) {
                    Toast.makeText(this, "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    id = edtidsanpham.getText().toString();
                    Products products = listproduct.get(0);
                    edttensp.getEditText().setText(products.getName_products());
                    edtinventory.getEditText().setText(String.valueOf(products.getInventory()));
                    edtprice.getEditText().setText(String.valueOf(products.getPrice()));
                    edtmota.getEditText().setText(products.getDescribe());
                    spinner.setSelection(products.getId_category() - 1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(products.getImg_product(), 0, products.getImg_product().length);
                    img.setImageBitmap(bitmap);
                    btnxoa = findViewById(R.id.btnsua);
                    btnxoa.setEnabled(true);
                    btnxoa.setOnClickListener(v1 -> {
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
                                Toast.makeText(DeleteProduct.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DeleteProduct.this, NavigationActivity.class);
                                intent.putExtra("username", getIntent().getStringExtra("username"));
                                startActivity(intent);
                            }
                        });
                        builder.show();
                    });
                }
            }
        });
    }
}