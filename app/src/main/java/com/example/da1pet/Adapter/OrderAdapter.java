package com.example.da1pet.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Order;
import com.example.da1pet.Model.User;
import com.example.da1pet.R;

import java.util.Calendar;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    List<Order> list;
    DbRoom db;

    public OrderAdapter(Context context, List<Order> list) {
        this.context = context;
        this.list = list;
        this.db = DbRoom.getInstance(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoa_don,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int index = position;
        Order order = list.get(index);
        User user = db.userDAO().getUserByCode(order.getId_order());
        List<User> list = db.userDAO().getAll();

        holder.tvNgay.setText("Ngày: " + order.getDate());
        if(order.getThanhToan() == 0){
            holder.tvThanhToan.setText("Chưa thanh Toán");
            holder.tvThanhToan.setTextColor(Color.parseColor("#D2001A"));
        }else {
            holder.tvThanhToan.setText("Đã thanh toán");
            holder.tvThanhToan.setTextColor(Color.BLUE);
        }
        holder.tvMaHoaDon.setText("Mã hóa đơn: " + order.getId_order());
        holder.tvIdUser.setText("ID user: " + user.getId_user());
        holder.tvTotal.setText("Total" + order.getTotal());
        holder.tvThanhToan.setText("Thanh toán: " + order.getThanhToan());
        holder.imgDelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete");
            builder.setMessage("Bạn muốn xóa!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        db.orderDAO().delete(order);
                        list.remove(index);
                        notifyItemRemoved(index);
                        notifyItemRangeChanged(index,getItemCount());
                        dialogInterface.dismiss();
                        Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

//        holder.itemView.setOnClickListener(view -> {
//            //Show dialog
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            View viewUpdate = LayoutInflater.from(context).inflate(R.layout.dialog_update_pm,null);
//            builder.setView(viewUpdate);
//            AlertDialog dialog = builder.create();
//            dialog.show();
//            //ánh xạ
//            TextView tvMaPM = viewUpdate.findViewById(R.id.tvMaPM);
//            Spinner spTV = viewUpdate.findViewById(R.id.spTV);
//            Spinner spSach = viewUpdate.findViewById(R.id.spSach);
//            EditText edNgay = viewUpdate.findViewById(R.id.edNgay);
//            EditText edTienThue = viewUpdate.findViewById(R.id.edTienThue);
//            CheckBox chkTraSach = viewUpdate.findViewById(R.id.chkTraSach);
//            Button btnUpdate = viewUpdate.findViewById(R.id.btnUpdate);
//            Button btnCancel =viewUpdate.findViewById(R.id.btnCancel);
//            //fill table
//            tvMaPM.setText("Mã phiếu: " + phieuMuon.getMaPM());
//            int indexTV = 0, indexSach = 0;
//            for (int i = 0; i < listTV.size(); i++) {
//                if(listTV.get(i).getMaTV().equals(phieuMuon.getMaTV())){
//                    indexTV = i;
//                    break;
//                }
//            }
//            for (int i = 0; i < listSach.size(); i++) {
//                if(listSach.get(i).getMaSach().equals(phieuMuon.getMaSach())){
//                    indexSach = i;
//                    break;
//                }
//            }
//            spTV.setAdapter(adapterTV);
//            spTV.setSelection(indexTV);
//            spSach.setAdapter(adapterSach);
//            spSach.setSelection(indexSach);
//            edNgay.setText(phieuMuon.getNgay());
//            edTienThue.setText(phieuMuon.getTienThue() + "");
//            chkTraSach.setChecked(phieuMuon.getTraSach() == 1);
//            //set up
//            edNgay.setOnClickListener(view1 -> {
//                //get day,month,year of systems
//                Calendar calendar= Calendar.getInstance();
//                final  int day = calendar.get(Calendar.DAY_OF_MONTH);
//                final  int month = calendar.get(Calendar.MONTH);
//                final  int year = calendar.get(Calendar.YEAR);
//                // DatePickerDialog
//                DatePickerDialog dp = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        String month = (i1+1)+"";
//                        String day = i2 +"";
//                        if((i1+1)<10){
//                            month = "0" + month;
//                        }
//                        if(i2<10){
//                            day = "0" + day;
//                        }
//                        edNgay.setText(i+"-"+month+"-"+day);
//                    }
//                },day,month,year);
//                dp.show();
//            });
//
////            btnUpdate.setOnClickListener(view1 -> {
////                if(adapterSach.isEmpty() || adapterTV.isEmpty() || edTienThue.getText().toString().isEmpty()){
////                    Toast.makeText(context, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                Sach book2 = (Sach) spSach.getSelectedItem();
////                ThanhVien member2 = (ThanhVien) spTV.getSelectedItem();
////
////                phieuMuon.setMaSach(book2.getMaSach());
////                phieuMuon.setMaTV(member2.getMaTV());
////                phieuMuon.setNgay(edNgay.getText().toString());
////                phieuMuon.setTienThue(Integer.parseInt(edTienThue.getText().toString()));
////                phieuMuon.setTraSach(chkTraSach.isChecked() ? 1 : 0);
////                try {
////                    db.phieuMuonDAO().updatePM(phieuMuon);
////                    list.set(position,phieuMuon);
////                    this.notifyItemChanged(position);
////                    dialog.dismiss();
////                    Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
////                }catch (Exception e){
////                    Toast.makeText(context, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
////                }
////            });
////            btnCancel.setOnClickListener(view1 -> {
////                dialog.dismiss();
////            });
//        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaHoaDon, tvIdUser, tvTotal, tvThanhToan, tvNgay;
        ImageView imgDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            tvMaHoaDon = itemView.findViewById(R.id.tvMaHoaDon);
            tvIdUser = itemView.findViewById(R.id.tvIdUser);
            tvTotal = itemView.findViewById(R.id.tv_total);
            tvThanhToan = itemView.findViewById(R.id.tvTrangThai);
            tvNgay = itemView.findViewById(R.id.tvNgay);

            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}