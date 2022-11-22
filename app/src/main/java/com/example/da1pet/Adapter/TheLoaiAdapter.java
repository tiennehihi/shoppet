package com.example.da1pet.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.Categorys;
import com.example.da1pet.R;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    List<Categorys> list;
    Context context;
    DbRoom db;
    public TheLoaiAdapter(Context context,List<Categorys> list) {
        this.list = list;
        this.context = context;
        this.db = DbRoom.getInstance(context);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_the_loai,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Categorys categorys = list.get(position);
        final int index = position;
        holder.tvTenLoai.setText(list.get(position).getTenLoai());
        holder.tvMaLoai.setText("Mã loại: "+list.get(position).getId_category());
        holder.imgDelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Cảnh báo");
            builder.setMessage("Xóa loại sách sẽ làm mất dữ liệu sách tương ứng.\n Bạn vẫn muốn tiếp tục!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try{
                        db.categoryDAO().deleteTLoai(categorys);
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

        holder.itemView.setOnClickListener(view -> {
            View viewUpdate = LayoutInflater.from(context).inflate(R.layout.dialog_update_the_loai,null );
            TextView tvMaLoai = viewUpdate.findViewById(R.id.tvMaLoai);
            EditText edTenLoai = viewUpdate.findViewById(R.id.edTenLoai);
            Button btnUpdate = viewUpdate.findViewById(R.id.btnSaveLS);
            Button btnCancel = viewUpdate.findViewById(R.id.btnCancelLS);
            //Fill table
            tvMaLoai.setText("Mã loại: "+categorys.getId_category());
            edTenLoai.setText(categorys.getTenLoai());
            //Dialog
            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(viewUpdate);
            alertDialog = builder.create();

            btnCancel.setOnClickListener(view1 -> {
                alertDialog.dismiss();
            });

            btnUpdate.setOnClickListener(view1 -> {
                if(edTenLoai.getText().toString().isEmpty()){
                    Toast.makeText(context, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }
                categorys.setTenLoai(edTenLoai.getText().toString());
                try{
                    db.categoryDAO().updateTLoai(categorys);
                    list.set(position,categorys);
                    this.notifyItemChanged(position);
                    Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }catch (Exception e){
                    Toast.makeText(context, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.show();
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaLoai, tvTenLoai;
        ImageView imgDelete;

        public ViewHolder( View itemView) {
            super(itemView);
            this.imgDelete = itemView.findViewById(R.id.imgDelete);
            this.tvMaLoai = itemView.findViewById(R.id.tvMaLoai);
            this.tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
        }
    }

}
