package com.example.da1pet.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.da1pet.Adapter.TheLoaiAdapter;
import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Categorys;
import com.example.da1pet.R;
import com.example.da1pet.databinding.FragmentTheLoaiBinding;

import java.util.List;


public class TheLoaiFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_the_loai, container, false);
        FragmentTheLoaiBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_the_loai,container,false);
        Context context = getActivity();

        DbRoom db= DbRoom.getInstance(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        binding.rvTLoai.setLayoutManager(manager);
        List<Categorys> list = db.categoryDAO().getAll();
        TheLoaiAdapter adapter = new TheLoaiAdapter(context,list);
        binding.rvTLoai.setAdapter(adapter);


        binding.fabAdd.setOnClickListener(view1 -> {
            View viewAdd = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_the_loai,null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(viewAdd);
            AlertDialog dialog = builder.create();
            EditText edTenLoai = viewAdd.findViewById(R.id.edTenLoai);
            Button btnAddLS = viewAdd.findViewById(R.id.btnAddTLoai);
            Button btnCancelLS = viewAdd.findViewById(R.id.btnCancelTLoai);
            btnCancelLS.setOnClickListener(view2 -> {
                dialog.dismiss();
            });
            btnAddLS.setOnClickListener(view2 -> {
                if(edTenLoai.getText().toString().isEmpty()){
                    Toast.makeText(context, "Bạn vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String tenLoai = edTenLoai.getText().toString();
                try{
                    db.categoryDAO().insertTLoai(new Categorys(tenLoai));
                    Categorys categorys = db.categoryDAO().getCategoryByName(tenLoai);
                    list.add(categorys);
                    adapter.notifyItemInserted(list.size()-1);
                    Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }catch (Exception e){
                    Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        });
        return binding.getRoot();
    }

}