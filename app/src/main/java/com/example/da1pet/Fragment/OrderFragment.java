package com.example.da1pet.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.da1pet.Adapter.OrderAdapter;
import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Order;
import com.example.da1pet.Model.User;
import com.example.da1pet.Model.UserViewModel;
import com.example.da1pet.R;
import com.example.da1pet.databinding.FragmentOrderBinding;

import java.util.Calendar;
import java.util.List;


public class OrderFragment extends Fragment {
    FragmentOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container,false );

        UserViewModel model = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        model.getUser().observe(getActivity(),item -> {
            User user = (User) item;
            Context context = getActivity();
            DbRoom db = DbRoom.getInstance(context);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            binding.rvOrder.setLayoutManager(manager);
            List<Order> list = db.orderDAO().getAll();
            OrderAdapter adapter = new OrderAdapter(context,list);
            binding.rvOrder.setAdapter(adapter);
            binding.fabAdd.setOnClickListener(view1 -> {
                //Show dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View viewAdd = LayoutInflater.from(context).inflate(R.layout.dialog_add_hoa_don,null);
                builder.setView(viewAdd);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                //Anh xa
                EditText ed_idOrder = viewAdd.findViewById(R.id.ed_idOrder);
                EditText ed_Total = viewAdd.findViewById(R.id.ed_Total);
                EditText ed_Ngay = viewAdd.findViewById(R.id.ed_Ngay);
                CheckBox chkThanhToan = viewAdd.findViewById(R.id.chkThanhToan);
                Button btnAdd = viewAdd.findViewById(R.id.btnAdd);
                Button btnCancel = viewAdd.findViewById(R.id.btnCancel);
                //init
                List<User> listU = db.userDAO().getAll();

                ed_Ngay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar= Calendar.getInstance();
                        final  int day = calendar.get(Calendar.DAY_OF_MONTH);
                        final  int month = calendar.get(Calendar.MONTH);
                        final  int year = calendar.get(Calendar.YEAR);
                        // DatePickerDialog
                        DatePickerDialog dp = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String month = (i1+1)+"";
                                String day = i2 +"";
                                if((i1+1)<10){
                                    month = "0" + month;
                                }
                                if(i2<10){
                                    day = "0" + day;
                                }
                                ed_Ngay.setText(i+"-"+month+"-"+day);
                            }
                        },day,month,year);
                        dp.show();
                    }
                });

                btnAdd.setOnClickListener(view2 -> {
                    if(ed_idOrder.getText().toString().isEmpty() ||ed_Total.getText().toString().isEmpty()){
                        Toast.makeText(context, "không được để trống!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Order order = new Order();
                    order.setId_order(order.getId_order());
                    order.setId_user(user.getId_user());
                    order.setThanhToan(Integer.valueOf(String.valueOf(chkThanhToan.isChecked()?1:0)));
                    order.setTotal(order.getTotal());
                    order.setDate(ed_Ngay.getText().toString());

                    try{
                        db.orderDAO().insert(order);
                        order.setId_order(db.orderDAO().getCode(order.getId_order(),order.getId_user()));
                        list.add(order);
                        adapter.notifyItemInserted(list.size()-1);
                        alertDialog.dismiss();
                        Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
                btnCancel.setOnClickListener(view2 -> alertDialog.dismiss());
            });
        });
        return binding.getRoot();
    }

}