package com.example.da1pet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Cart_item;
import com.example.da1pet.Model.Categorys;
import com.example.da1pet.Model.Order;
import com.example.da1pet.Model.Order_detail;
import com.example.da1pet.Model.Products;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProductActivity extends AppCompatActivity {
    DbRoom db;
    List<Products> list;
    String TAG = "zzzzzzzzzz";
    AlertDialog alertDialog;
    List<Categorys> categorysList;
    int a = 0;
    ImageView imageviewproduct;
    ArrayList<Order> listorder;
    ArrayList<Cart_item> listcart;
    TextView tvtensp,tvloaisp,tvsoluongsanpham,tvmotasanpham,tvtongtien,tvidsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        db = DbRoom.getInstance(this);
        imageviewproduct = findViewById(R.id.imageviewproduct);
        tvtensp = findViewById(R.id.tvtensp);
        tvloaisp = findViewById(R.id.tvloaisp);
        tvsoluongsanpham = findViewById(R.id.tvsoluongsanpham);
        tvmotasanpham = findViewById(R.id.tvmotasanpham);
        tvidsp = findViewById(R.id.tvidsp);
        findViewById(R.id.btnback).setOnClickListener(v -> {
            onBackPressed();
        });
        try {
            Bundle bundle = getIntent().getExtras();
            list = db.productsDAO().getItemById(bundle.getInt("idsanpham"));
            Products products = list.get(0);
            categorysList = db.categoryDAO().getItemById(products.getId_category());
            Categorys categorys = categorysList.get(0);

            Bitmap bitmap = BitmapFactory.decodeByteArray(products.getImg_product(),0,products.getImg_product().length);
            imageviewproduct.setImageBitmap(bitmap);
            tvtensp.setText(products.getName_products());
            tvloaisp.setText(categorys.getTenLoai());
            tvsoluongsanpham.setText(String.valueOf(products.getInventory()));
            tvmotasanpham.setText(products.getDescribe());
            tvidsp.setText(String.valueOf(products.getId_products()));
            findViewById(R.id.btnthemspgiohang).setOnClickListener(v -> {
                if (bundle.getString("username").equals("")){
                    Toast.makeText(this, "Hãy đăng nhập để dùng chức năng này", Toast.LENGTH_SHORT).show();
                }else {
                    listcart = (ArrayList<Cart_item>) db.cartItemDAO().getAll();
                    a = 0;
                    try {
                        for (int i = 0; i < db.cartItemDAO().getAllCartItemById(bundle.getString("username")).size(); i++) {
                            Cart_item cartItem = listcart.get(i);
                            if (products.getId_products() == cartItem.getId_products()){
                                Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                                a++;
                                break;
                            }
                        }
                        if (a == 0){

                            db.cartItemDAO().insertCartItem(new Cart_item(bundle.getString("username"),bundle.getInt("idsanpham"),1));
                            Toast.makeText(this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
            });
            findViewById(R.id.btnbuypr).setOnClickListener(v -> {
                if (bundle.getString("username").equals("")){
                    Toast.makeText(this, "Bạn cần phải đăng nhập trước", Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder  builder = new AlertDialog.Builder(this);
                    View view = LayoutInflater.from(this).inflate(R.layout.dialog_buy,null,false);
                    builder.setView(view);
                    alertDialog = builder.show();
                    Window window = alertDialog.getWindow();
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                    WindowManager.LayoutParams windowManager = window.getAttributes();
                    windowManager.gravity = Gravity.BOTTOM;
                    window.setAttributes(windowManager);
                    ImageView imgpr = view.findViewById(R.id.imgpr);
                    imgpr.setImageBitmap(bitmap);
                    TextView tvkho = view.findViewById(R.id.tvkho);
                    tvkho.setText(String.valueOf(products.getInventory()));
                    TextView tvgia = view.findViewById(R.id.tvgiapr);
                    tvgia.setText(String.valueOf(products.getPrice()));
                    Button tbtn = view.findViewById(R.id.tbtn);
                    Button gbtn = view.findViewById(R.id.gbtn);
                    tvtongtien = view.findViewById(R.id.tvtongtien);

                    TextView tvsoluong = view.findViewById(R.id.tvsoluoong);
                    tvtongtien.setText(String.valueOf(Integer.parseInt(tvgia.getText().toString())*Integer.parseInt(tvsoluong.getText().toString())));
                    tbtn.setOnClickListener(v1 -> {
                        if (Integer.parseInt(tvsoluong.getText().toString())==Integer.parseInt(tvsoluongsanpham.getText().toString())){
                            tbtn.setEnabled(false);
                        }else {
                            tvsoluong.setText(String.valueOf(Integer.parseInt(tvsoluong.getText().toString())+1));
                            tvtongtien.setText(String.valueOf(Integer.parseInt(tvgia.getText().toString())*Integer.parseInt(tvsoluong.getText().toString())));
                            tbtn.setEnabled(true);
                            if (Integer.parseInt(tvsoluong.getText().toString())==1){
                                gbtn.setEnabled(false);
                            }else {
                                gbtn.setEnabled(true);
                                gbtn.setOnClickListener(v2 -> {
                                    tvsoluong.setText(String.valueOf(Integer.parseInt(tvsoluong.getText().toString())-1));
                                    tvtongtien.setText(String.valueOf(Integer.parseInt(tvgia.getText().toString())*Integer.parseInt(tvsoluong.getText().toString())));
                                    tbtn.setEnabled(true);
                                    if (Integer.parseInt(tvsoluong.getText().toString())==1){
                                        gbtn.setEnabled(false);
                                    }
                                });
                            }
                        }
                    });
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    view.findViewById(R.id.btnthanhtoan).setOnClickListener(v1 -> {

                           try {
                               db.orderDAO().insert(
                                       new Order(bundle.getString("username")
                                               ,1,
                                               currentDate,Integer.parseInt(tvtongtien.getText().toString()),"Chờ xác nhận"));
                               listorder = (ArrayList<Order>) db.orderDAO().getAll();
                               Order order = listorder.get(listorder.size()-1);
                               db.orderDetailDAO().insert(
                                       new Order_detail(order.getId_order(),bundle.getInt("idsanpham")
                                               ,Integer.parseInt(tvsoluong.getText().toString())));
                           }catch (Exception e){
                               Log.e(TAG, "onCreate: "+e.getMessage());
                           }
                           db.productsDAO().update(new Products(products.getId_products(),products.getId_category(),products.getInventory()-Integer.parseInt(tvsoluong.getText().toString()),products.getName_products(),products.getPrice(),products.getDescribe(),products.getImg_product()));
                           alertDialog.dismiss();
                        Toast.makeText(this, "Mua thành công", Toast.LENGTH_SHORT).show();
                        try {
                            Bundle bundle1 = getIntent().getExtras();
                            list = db.productsDAO().getItemById(bundle1.getInt("idsanpham"));
                            Products products1 = list.get(0);
                            categorysList = db.categoryDAO().getItemById(products1.getId_category());
                            Categorys categorys1 = categorysList.get(0);

                            Bitmap bitmap1 = BitmapFactory.decodeByteArray(products1.getImg_product(), 0, products1.getImg_product().length);
                            imageviewproduct.setImageBitmap(bitmap1);
                            tvtensp.setText(products1.getName_products());
                            tvloaisp.setText(categorys1.getTenLoai());
                            tvsoluongsanpham.setText(String.valueOf(products1.getInventory()));
                            tvmotasanpham.setText(products1.getDescribe());
                        }catch (Exception e){
                            e.getMessage();
                        }
                    });
                }

            });
        }catch (Exception e){
            Log.e(TAG, "onCreate: "+ e.getMessage());
        }
    }
}