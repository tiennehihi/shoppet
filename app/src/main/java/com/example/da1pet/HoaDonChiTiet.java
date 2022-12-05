package com.example.da1pet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Order;
import com.example.da1pet.Model.Order_detail;
import com.example.da1pet.Model.Products;
import com.example.da1pet.innerjoin.InnerJoin;
import com.example.da1pet.innerjoin.InnerOrder;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTiet extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<InnerJoin> list;
    String TAG = "zzzzzzz";
    DbRoom db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        db = DbRoom.getInstance(this);
        findViewById(R.id.btnback).setOnClickListener(v -> {
            onBackPressed();
        });
        rv = findViewById(R.id.rvorderdetail);
        Bundle bundle = getIntent().getExtras();
        list = (ArrayList<InnerJoin>) db.orderDetailDAO().getOrder(bundle.getInt("id_order"));
        int tong = 0;
        for (int i = 0; i < list.size(); i++) {
            InnerJoin innerJoin = list.get(i);
            tong += innerJoin.getGia();
        }
        TextView tvtongtien = findViewById(R.id.tvtongtien);
        tvtongtien.setText(tong + " VNĐ");
        OrderAdapter adapter = new OrderAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        Button btnnhandon = findViewById(R.id.btnnhandon);
        Button btnhuydon = findViewById(R.id.btnhuydon);
        List<String> listorder = db.orderDAO().getstatus(bundle.getInt("id_order"));
        try {
            if (listorder.get(0).equals("Chờ xác nhận")) {
                btnnhandon.setEnabled(false);
                btnnhandon.setBackgroundColor(Color.parseColor("#EC6565"));
            } else if (listorder.get(0).equals("Đang giao hàng")) {
                btnhuydon.setEnabled(false);
            } else if (listorder.get(0).equals("Đã Nhận hàng") || listorder.get(0).equals("Đơn hàng đã hủy") || listorder.get(0).substring(0, 15).equals("Đơn hàng đã hủy")) {
                btnnhandon.setEnabled(false);
                btnnhandon.setBackgroundColor(Color.parseColor("#EC6565"));
                btnhuydon.setEnabled(false);
            }
        }catch (Exception e){
            e.getMessage();
        }

        findViewById(R.id.btnnhandon).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn đã nhận hàng?");
            builder.setPositiveButton("Đã Nhận hàng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.orderDAO().updateStatus("Đã Nhận hàng", bundle.getInt("id_order"));
                    Intent intent = new Intent(HoaDonChiTiet.this, HoaDon.class);
                    intent.putExtra("username", getIntent().getExtras().getString("username"));
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Chưa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
             builder.show();
        });
        findViewById(R.id.btnhuydon).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn hủy đơn?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(HoaDonChiTiet.this, "Bạn đã hủy đơn", Toast.LENGTH_SHORT).show();
                    db.orderDAO().updateStatus("Đơn hàng đã hủy", bundle.getInt("id_order"));
                    Intent intent = new Intent(HoaDonChiTiet.this, HoaDon.class);
                    intent.putExtra("username", getIntent().getExtras().getString("username"));
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();


        });
    }

    public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
        ArrayList<InnerJoin> list;

        public OrderAdapter(ArrayList<InnerJoin> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roworderdetail, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.innerOrder = list.get(position);
            holder.tvtenpr.setText(holder.innerOrder.getNameproduct());
            holder.tvgiapr.setText(String.valueOf(holder.innerOrder.getGia()));
            holder.tvsoluong.setText(String.valueOf(holder.innerOrder.getQuantity()));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            InnerJoin innerOrder;
            TextView tvtenpr, tvgiapr, tvsoluong;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvtenpr = itemView.findViewById(R.id.tvnamepr);
                tvgiapr = itemView.findViewById(R.id.tvgiapr);
                tvsoluong = itemView.findViewById(R.id.tvsoluong);
            }
        }
    }
}