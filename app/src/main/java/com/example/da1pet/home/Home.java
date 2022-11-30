package com.example.da1pet.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.LoginActivity;
import com.example.da1pet.Model.Products;
import com.example.da1pet.NavigationActivity;
import com.example.da1pet.ProductActivity;
import com.example.da1pet.R;
import com.example.da1pet.Shop.Shoppet;

import java.util.ArrayList;


public class Home extends Fragment {
    DbRoom db;
    ArrayList<Products> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = DbRoom.getInstance(this.getActivity());
        list = (ArrayList<Products>) db.productsDAO().getAll();
        ImageView slideshow = view.findViewById(R.id.homeslideshow);
        slideshow.setBackgroundResource(R.drawable.home_slideshow);
        AnimationDrawable animationDrawable = (AnimationDrawable) slideshow.getBackground();
        animationDrawable.start();
        ImageButton btn = view.findViewById(R.id.btnshop);
        btn.setOnClickListener(v -> {
            if (getActivity().getIntent().getStringExtra("username").equals("")){
                Intent intent = new Intent(this.getActivity(), LoginActivity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(this.getActivity(), Shoppet.class);
                intent.putExtra("idcart",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }

        });
        NonScrollGridView gridView = view.findViewById(R.id.lv);
        gridView.setNumColumns(2);
        gridView.setAdapter(new GrAdapter(list));
        gridView.setExpanded(true);
    }
    public class GrAdapter extends BaseAdapter {
        ArrayList<Products> list;

        public GrAdapter(ArrayList<Products> list) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progrid,parent,false);
            ImageView img = convertView.findViewById(R.id.imggrid);
            TextView tvten = convertView.findViewById(R.id.tenspgrid);
            TextView tvgia = convertView.findViewById(R.id.giagrid);
            try {
                Products products = list.get(position);
                Bitmap bitmap = BitmapFactory.decodeByteArray(products.getImg_product(),0,products.getImg_product().length);
                img.setImageBitmap(bitmap);
                tvgia.setText(String.valueOf(products.getPrice()));
                tvten.setText(products.getName_products());
                convertView.setOnClickListener(v -> {
                    Intent intent = new Intent(Home.this.getActivity(), ProductActivity.class);
                    intent.putExtra("idsanpham",products.getId_products());
                    intent.putExtra("username",getActivity().getIntent().getStringExtra("username"));
                    startActivity(intent);
                });
            }catch (Exception e){
                e.getMessage();
            }
            return convertView;
        }
    }

}