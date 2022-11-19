package com.example.da1pet.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.LaucherActivity;
import com.example.da1pet.Model.Products;
import com.example.da1pet.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    Context context;
    List<Products> list;
    DbRoom db;
    Button btnXem1;


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
        ImageView slideshow = view.findViewById(R.id.homeslideshow);
        slideshow.setBackgroundResource(R.drawable.home_slideshow);
        AnimationDrawable animationDrawable = (AnimationDrawable) slideshow.getBackground();
        animationDrawable.start();

    }




    public class GrAdapter extends BaseAdapter {
        ArrayList<Integer> list;

        public GrAdapter(ArrayList<Integer> list) {
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

            return convertView;
        }
    }


}