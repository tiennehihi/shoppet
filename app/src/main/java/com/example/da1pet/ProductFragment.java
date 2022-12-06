package com.example.da1pet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Products;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    RecyclerView rv;
    ArrayList<Products> list;
    DbRoom db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.rvsanpham);
        db = DbRoom.getInstance(this.getActivity());
        list = (ArrayList<Products>) db.productsDAO().getAll();
        RVAdapter adapter = new RVAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        view.findViewById(R.id.flbtn).setOnClickListener(v -> {
            Intent intent = new Intent(ProductFragment.this.getActivity(), ThemSanPham.class);
            intent.putExtra("username",getActivity().getIntent().getStringExtra("username"));
            startActivity(intent);
        });

    }
    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{
        ArrayList<Products> list;

        public RVAdapter(ArrayList<Products> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowproduct,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
            holder.products = list.get(position);
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(holder.products.getImg_product(), 0, holder.products.getImg_product().length));
            holder.tvtensp.setText(holder.products.getName_products());
            holder.tvsoluong.setText("x"+holder.products.getInventory());
            holder.tvgia.setText(holder.products.getPrice()+" VNĐ");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvtensp,tvsoluong,tvgia;
            ImageView img;
            Products products;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.img);
                tvtensp = itemView.findViewById(R.id.tvtensp);
                tvsoluong = itemView.findViewById(R.id.tvsoluong);
                tvgia = itemView.findViewById(R.id.tvgia);
                itemView.setOnClickListener(v -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductFragment.this.getActivity());
                    builder.setMessage("Bạn có muốn sửa thông tin sản phẩm không?");
                    builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ProductFragment.this.getActivity(), UpdateProduct.class);
                            intent.putExtra("username",getActivity().getIntent().getStringExtra("username"));
                            Products products = list.get(getAdapterPosition());
                            intent.putExtra("idpr",products.getId_products());
                            startActivity(intent);
                        }
                    });
                    builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                });
            }
        }
    }
}