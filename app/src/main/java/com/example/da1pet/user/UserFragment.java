package com.example.da1pet.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.da1pet.Lienhe;
import com.example.da1pet.LoginActivity;
import com.example.da1pet.R;
import com.example.da1pet.ThemSanPham;


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
        view.findViewById(R.id.btnlogin).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
        view.findViewById(R.id.tvthemsp).setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), ThemSanPham.class);
            startActivity(intent);
        });
        view.findViewById(R.id.btnlienhe).setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), Lienhe.class);
            startActivity(intent);
        });
    }
}