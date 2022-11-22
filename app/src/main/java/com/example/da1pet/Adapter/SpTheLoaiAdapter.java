package com.example.da1pet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.da1pet.Model.Categorys;
import com.example.da1pet.R;

import java.util.List;

public class SpTheLoaiAdapter extends ArrayAdapter {
    List<Categorys> list;
    Context context;


    public SpTheLoaiAdapter( Context context, List<Categorys> list) {
        super(context, 0,list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    public View createView(int position, View convertView){
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner_the_loai,null);
            holder.tvMaLoai = convertView.findViewById(R.id.tvMaLoai);
            holder.tvTenLoai = convertView.findViewById(R.id.tvTenLoai);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTenLoai.setText(list.get(position).getTenLoai());
        holder.tvMaLoai.setText(list.get(position).getId_category()+ ". ");
        return convertView;
    }

    public class ViewHolder{
        TextView tvMaLoai,tvTenLoai;
    }

}
