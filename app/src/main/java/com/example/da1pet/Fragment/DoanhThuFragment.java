package com.example.da1pet.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;


public class DoanhThuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        TextInputLayout txtTuNgay = view.findViewById(R.id.edTuNgay);
        TextInputLayout txtDenNgay = view.findViewById(R.id.edDenNgay);
        Button btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        TextView tvDoanhThu = view.findViewById(R.id.tvDoanhThu);

        Context context = getActivity();
        DbRoom db = DbRoom.getInstance(context);

        Calendar calendar= Calendar.getInstance();
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);
        final  int month = calendar.get(Calendar.MONTH);
        final  int year = calendar.get(Calendar.YEAR);

        txtTuNgay.getEditText().setOnClickListener(view1 -> {

                DatePickerDialog dp = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                    txtTuNgay.getEditText().setText(dayOfMonth+"-"+(month+1)+"-"+year);
                }
            },year,month,day);
            dp.show();

            // DatePickerDialog
            //Date currentTime = Calendar.getInstance().getTime();
        //    DatePickerDialog dp = new DatePickerDialog(context, (datePicker, i, i1, i2) ->{
//                String month2 = (i1+1)+"";
//                String day2 = i2 +"";
//                if((i1+1)<10)
//                    month2 = "0" + month2;
//                if(i2<10)
//                    day2 = "0" + day2;
            //    txtTuNgay.getEditText().setText(day+"-"+month+"-"+year);
            //},day,month,year);

        });

        txtDenNgay.getEditText().setOnClickListener(view1 -> {
            // DatePickerDialog
            DatePickerDialog dp = new DatePickerDialog(context, (datePicker, i, i1, i2) ->{
                String month2 = (i1+1)+"";
                String day2 = i2 +"";
                if((i1+1)<10)
                    month2 = "0" + month2;
                if(i2<10)
                    day2 = "0" + day2;
                txtDenNgay.getEditText().setText(i+"-"+month2+"-"+day2);
            },day,month,year);
            dp.show();
        });

        btnDoanhThu.setOnClickListener(view1 -> {
            String tuNgay = txtTuNgay.getEditText().getText().toString();
            String denNgay = txtDenNgay.getEditText().getText().toString();
            if (tuNgay.isEmpty()){
                txtTuNgay.setError("Vui lòng không để trống");
                return;
            }
            txtTuNgay.setError("");

            if (denNgay.isEmpty()){
                txtDenNgay.setError("Vui lòng không để trống");
                return;
            }
            txtDenNgay.setError("");

            int doanhThu = db.orderDAO().getDoanhThu(tuNgay,denNgay);
            tvDoanhThu.setText("Doanh thu: " + doanhThu);
        });


        return view ;
    }
}