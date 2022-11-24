package com.example.da1pet.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.User;
import com.example.da1pet.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<User> list;
    Context context;
    DbRoom db;
    public UserAdapter(Context context, List<User> list) {
        this.list = list;
        this.context = context;
        this.db = DbRoom.getInstance(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = list.get(position);
        final int index = position;
        holder.tvName.setText(list.get(position).getName());
        holder.tvID.setText("ID: "+list.get(position).getId_user());
        holder.tvNumber.setText(list.get(position).getNumber());
        holder.imgDelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Cảnh báo");
            builder.setMessage("Xóa loại sách sẽ làm mất dữ liệu sách tương ứng.\n Bạn vẫn muốn tiếp tục!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try{
                        db.userDAO().delete(user);
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
            View viewUpdate = LayoutInflater.from(context).inflate(R.layout.dialog_update_users,null );
            TextView tvIdUser = viewUpdate.findViewById(R.id.tvId);
            EditText ed_Name = viewUpdate.findViewById(R.id.ed_Name);
            EditText ed_Number = viewUpdate.findViewById(R.id.ed_Number);
            Button btnUpdate = viewUpdate.findViewById(R.id.btnSave);
            Button btnCancel = viewUpdate.findViewById(R.id.btnCancel);
            //Fill table
            tvIdUser.setText("ID: "+user.getId_user());
            ed_Name.setText(user.getName());
            ed_Number.setText(user.getNumber());
            //Dialog
            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(viewUpdate);
            alertDialog = builder.create();

            btnCancel.setOnClickListener(view1 -> {
                alertDialog.dismiss();
            });
            btnUpdate.setOnClickListener(view1 -> {
                if(ed_Name.getText().toString().isEmpty()){
                    Toast.makeText(context, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setName(ed_Name.getText().toString());
                try{
                    db.userDAO().update(user);
                    list.set(position,user);
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
        TextView tvName,tvID,tvNumber;
        ImageView imgDelete;

        public ViewHolder( View itemView) {
            super(itemView);
            this.imgDelete = itemView.findViewById(R.id.imgDelete);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvID = itemView.findViewById(R.id.tvIdUser);
            this.tvNumber = itemView.findViewById(R.id.tvNumber);
        }
    }

}
