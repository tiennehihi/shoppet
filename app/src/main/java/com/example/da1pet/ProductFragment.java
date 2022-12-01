package com.example.da1pet;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProductFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnthemsanpham).setOnClickListener(v -> {
            Intent intent = new Intent(ProductFragment.this.getActivity(), ThemSanPham.class);
            intent.putExtra("username",getActivity().getIntent().getStringExtra("username"));
            startActivity(intent);
        });
        view.findViewById(R.id.btnsuasanpham).setOnClickListener(v -> {
            Intent intent = new Intent(ProductFragment.this.getActivity(), UpdateProduct.class);
            intent.putExtra("username",getActivity().getIntent().getStringExtra("username"));
            startActivity(intent);
        });
        view.findViewById(R.id.btnxoasanpham).setOnClickListener(v -> {
            Intent intent = new Intent(ProductFragment.this.getActivity(), DeleteProduct.class);
            intent.putExtra("username",getActivity().getIntent().getStringExtra("username"));
            startActivity(intent);
        });
    }
}