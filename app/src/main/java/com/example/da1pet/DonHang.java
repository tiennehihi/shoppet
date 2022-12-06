package com.example.da1pet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Order;
import com.example.da1pet.Model.User;
import com.example.da1pet.innerjoin.InnerThongTInDonHang;

import java.util.ArrayList;
import java.util.List;

public class DonHang extends AppCompatActivity {
    RecyclerView rv;
    TextView tvname, tvsdt, tvaddress, tvtongtien;
    DbRoom db;
    AlertDialog alertDialog;
    ArrayList<InnerThongTInDonHang> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        db = DbRoom.getInstance(this);

        tvtongtien = findViewById(R.id.tvtongtien);
        Bundle bundle = getIntent().getExtras();
        list = (ArrayList<InnerThongTInDonHang>) db.orderDetailDAO().getThongTinDonHang(bundle.getInt("id_order"));
        List<User> userList = db.userDAO().getuser(bundle.getString("username1"));
        User user = userList.get(0);
        findViewById(R.id.btnback).setOnClickListener(v -> {
            Intent intent = new Intent(this,NavigationActivity.class);
            intent.putExtra("username",bundle.getString("username1"));
            intent.putExtra("item","donhang");
            startActivity(intent);
        });
        tvname = findViewById(R.id.tvname);
        tvsdt = findViewById(R.id.tvsdt);
        tvaddress = findViewById(R.id.tvaddress);
        tvname.setText(user.getName());
        tvsdt.setText(String.valueOf(user.getNumber()));
        tvaddress.setText(user.getAddress());
        ArrayList<Order> orderArrayList = (ArrayList<Order>) db.orderDAO().getAllById(bundle.getInt("id_order"));
        Order order = orderArrayList.get(0);
        tvtongtien.setText(order.getThanhToan() + " VNĐ");
        rv = findViewById(R.id.rvdonhang);
        RVAdapter adapter = new RVAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        findViewById(R.id.btngiaohang).setOnClickListener(v -> {
            db.orderDAO().updateStatus("Đang giao hàng", bundle.getInt("id_order"));
            Toast.makeText(this, "Đơn hàng đang được giao", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,NavigationActivity.class);
            intent.putExtra("username",bundle.getString("username1"));
            intent.putExtra("item","donhang");
            startActivity(intent);
        });
        findViewById(R.id.btnhuydon).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_huydon, null, false);
            builder.setView(view);
            EditText edthuydon = view.findViewById(R.id.edthuydon);
            view.findViewById(R.id.btnhuy).setOnClickListener(v1 -> {
                alertDialog.dismiss();
            });
            view.findViewById(R.id.btnhd).setOnClickListener(v1 -> {
                db.orderDAO().updateStatus("Đơn hàng đã hủy ( " + edthuydon.getText().toString() + ")", bundle.getInt("id_order"));
                Toast.makeText(this, "Đơn hàng đã bị hủy", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                Intent intent = new Intent(this,NavigationActivity.class);
                intent.putExtra("username",bundle.getString("username1"));
                intent.putExtra("item","donhang");
                startActivity(intent);
            });
            alertDialog = builder.show();
        });
    }

    private class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
        ArrayList<InnerThongTInDonHang> list;

        public RVAdapter(ArrayList<InnerThongTInDonHang> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowthongtindonhang, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
            holder.inner = list.get(position);
            holder.tvtensp.setText(holder.inner.getName());
            holder.tvprice.setText(holder.inner.getSoluong() * holder.inner.getPrice() + " VNĐ");
            holder.soluong.setText("x" + holder.inner.getSoluong());
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(holder.inner.getImg(), 0, holder.inner.getImg().length));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvtensp, soluong, tvprice;
            InnerThongTInDonHang inner;
            ImageView img;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvtensp = itemView.findViewById(R.id.tvtensp);
                soluong = itemView.findViewById(R.id.soluong);
                tvprice = itemView.findViewById(R.id.tvprice);
                img = itemView.findViewById(R.id.img);
            }
        }
    }
}