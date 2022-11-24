package com.example.da1pet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Order_detail;
import com.example.da1pet.Model.Products;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity {
    ArrayList<Products> listpr;
    DbRoom db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        db = DbRoom.getInstance(this);
        Bundle bundle = getIntent().getExtras();
        listpr = (ArrayList<Products>) db.productsDAO().getItemById(bundle.getInt("idsanpham"));
    }
    public class gridAdapter extends BaseAdapter{
        ArrayList<Order_detail> list;

        public gridAdapter(ArrayList<Order_detail> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridthanhtoan,parent,false);
            ImageView img = convertView.findViewById(R.id.img);
            TextView tvten = convertView.findViewById(R.id.tvtensp);
            TextView tvtheloai = convertView.findViewById(R.id.tvtheloai);
            TextView tvgia = convertView.findViewById(R.id.tvgia);
            TextView tvsoluong = convertView.findViewById(R.id.tvsoluong);


            return convertView;
        }
    }
}