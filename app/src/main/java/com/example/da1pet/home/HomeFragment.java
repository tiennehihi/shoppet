package com.example.da1pet.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.LaucherActivity;
import com.example.da1pet.Model.Products;
import com.example.da1pet.ProductActivity;
import com.example.da1pet.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    Context context;
    ArrayList<Products> list;
    DbRoom db;
    Button btnXem1;
    NonScrollGridView gridView;
    String TAG = "zzzzz";

    //    public HomeFragment(Context context, List<Products> list) {
    //        this.context = context;
    //        this.list = list;
    //        this.db = DbRoom.getInstance(context);
    //    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = DbRoom.getInstance(this.getContext());
        ImageView slideshow = view.findViewById(R.id.homeslideshow);
        slideshow.setBackgroundResource(R.drawable.home_slideshow);
        AnimationDrawable animationDrawable = (AnimationDrawable) slideshow.getBackground();
        animationDrawable.start();
        list = new ArrayList<>();
        try {
                list = (ArrayList<Products>) db.productsDAO().getAll();
                GrAdapter adapter = new GrAdapter(list);
                gridView = view.findViewById(R.id.nonscrollgridview);
                gridView.setAdapter(adapter);
                gridView.setNumColumns(2);
        }catch (Exception e){
            Log.e(TAG, "onViewCreated: "+e.getMessage());
        }

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
            ImageView imageView = convertView.findViewById(R.id.imggrid);
            TextView textView = convertView.findViewById(R.id.tenspgrid);
            TextView textView1 = convertView.findViewById(R.id.giagrid);
            Products products = list.get(position);
            Bitmap bitmap = BitmapFactory.decodeByteArray(products.getImg_product(), 0, products.getImg_product().length);
            imageView.setImageBitmap(bitmap);
            textView.setText(products.getName_products());
            textView1.setText(String.valueOf(products.getPrice()));
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(HomeFragment.this.getActivity(), ProductActivity.class);
                startActivity(intent);
            });
            return convertView;
        }
    }


}