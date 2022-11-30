package com.example.da1pet.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.da1pet.HoaDon;
import com.example.da1pet.Lienhe;
import com.example.da1pet.LoginActivity;
import com.example.da1pet.NavigationActivity;
import com.example.da1pet.R;
import com.example.da1pet.Shop.Shoppet;
import com.example.da1pet.ThemSanPham;
import com.example.da1pet.Thongbao;


public class UserFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tvhoadon).setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), HoaDon.class);
            intent.putExtra("username",getActivity().getIntent().getStringExtra("username"));
            startActivity(intent);
        });
        view.findViewById(R.id.tvthemsp).setOnClickListener(v -> {
            try {
                Intent intent = new Intent(this.getActivity(), ThemSanPham.class);
                intent.putExtra("username",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }catch (Exception e){
                e.getMessage();
            }
        });
        view.findViewById(R.id.imglhe).setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), Lienhe.class);
            startActivity(intent);
        });
        view.findViewById(R.id.imgthongbao).setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), Thongbao.class);
            startActivity(intent);
        });
        view.findViewById(R.id.giohang).setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), Shoppet.class);
            intent.putExtra("idcart",getActivity().getIntent().getStringExtra("username"));
            startActivity(intent);
        });
    }
}