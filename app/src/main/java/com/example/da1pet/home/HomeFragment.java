package com.example.da1pet.home;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.da1pet.R;
import com.example.da1pet.Thongbao;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    ListView lv;
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

        //textFilter
//        lv.setTextFilterEnabled(true);
//        EditText edsearch = (EditText) view.findViewById(R.id.edtsearch);
//        edsearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        //---

        view.findViewById(R.id.imgthongb).setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), Thongbao.class);
            startActivity(intent);
        });

//        NonScrollGridView gridView = view.findViewById(R.id.lv);
//        gridView.setNumColumns(2);
//        gridView.setAdapter(new GrAdapter(list));
//        gridView.setExpanded(true);
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