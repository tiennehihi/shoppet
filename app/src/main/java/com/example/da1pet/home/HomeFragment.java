package com.example.da1pet.home;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da1pet.R;
import com.example.da1pet.TestProducts;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
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
        ArrayList<TestProducts> list = new ArrayList<>();
        list.add(new TestProducts(R.drawable.img2,"dog",1000000));
        list.add(new TestProducts(R.drawable.img2,"dog",1000000));
        list.add(new TestProducts(R.drawable.img2,"dog",1000000));
        list.add(new TestProducts(R.drawable.img2,"dog",1000000));
        list.add(new TestProducts(R.drawable.img2,"dog",1000000));

        NonScrollGridView gridView = view.findViewById(R.id.lv);
        gridView.setNumColumns(2);
        gridView.setAdapter(new GrAdapter(list));
        gridView.setExpanded(true);
    }
    public class GrAdapter extends BaseAdapter {
        ArrayList<TestProducts> list;

        public GrAdapter(ArrayList<TestProducts> list) {
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
            TextView tvtensp = convertView.findViewById(R.id.tenspgrid);
            TextView tvgia = convertView.findViewById(R.id.giagrid);
            TestProducts test = list.get(position);
            img.setImageResource(test.getImg());
            tvtensp.setText(test.getTensp());
            tvgia.setText(String.valueOf(test.getGia()));
            return convertView;
        }
    }

}