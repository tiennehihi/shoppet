package com.example.da1pet.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.DonHang;
import com.example.da1pet.Model.Order;
import com.example.da1pet.R;
import com.example.da1pet.Shop.Shoppet;
import com.example.da1pet.innerjoin.InnerDonHang;

import java.util.ArrayList;


public class DonHangFagment extends Fragment {
    DbRoom db;
    RecyclerView rv;
    ArrayList<InnerDonHang> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_don_hang_fagment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = DbRoom.getInstance(DonHangFagment.this.getActivity());
        rv = view.findViewById(R.id.rvdonhang);

        list = (ArrayList<InnerDonHang>) db.orderDAO().getByStatus("Chờ xác nhận");
        RVAdapter adapter = new RVAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Viewholder>{
        ArrayList<InnerDonHang> list;

        public RVAdapter(ArrayList<InnerDonHang> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RVAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowdonhang,parent,false);
            return new Viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RVAdapter.Viewholder holder, int position) {
            holder.donhang = list.get(position);
            holder.tvIdUser.setText(holder.donhang.getName());
            holder.tvdate.setText(holder.donhang.getDate());
            holder.tvsoluong.setText(holder.donhang.getTotal()+"item");
            holder.tvgia.setText(holder.donhang.getThanhToan()+" VNĐ");
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Viewholder extends RecyclerView.ViewHolder {
            InnerDonHang donhang;
            TextView tvIdUser,tvsoluong,tvgia,tvdate;
            public Viewholder(@NonNull View itemView) {
                super(itemView);
                tvIdUser = itemView.findViewById(R.id.tvIdUser);
                tvsoluong = itemView.findViewById(R.id.tvsoluong);
                tvgia = itemView.findViewById(R.id.tvgia);
                tvdate = itemView.findViewById(R.id.tvdate);
                itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(DonHangFagment.this.getActivity(), DonHang.class);
                    InnerDonHang donHang1 = list.get(getAdapterPosition());
                    intent.putExtra("id_order",donHang1.getId_order());
                    intent.putExtra("username1",donHang1.getId_user());
                    startActivity(intent);
                });
            }
        }
    }
}