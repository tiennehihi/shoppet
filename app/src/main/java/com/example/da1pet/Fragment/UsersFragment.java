package com.example.da1pet.Fragment;

import android.content.Context;
import android.os.Bundle;

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

import com.example.da1pet.Adapter.UserAdapter;
import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.User;
import com.example.da1pet.R;
import com.example.da1pet.databinding.FragmentUserBinding;
import com.example.da1pet.databinding.FragmentUsersBinding;

import java.util.List;


public class UsersFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        FragmentUsersBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_users,container,false);
        Context context = getActivity();
        DbRoom db= DbRoom.getInstance(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        binding.rvUsers.setLayoutManager(manager);
        List<User> list = db.userDAO().getAll();
        UserAdapter adapter = new UserAdapter(context,list);
        binding.rvUsers.setAdapter(adapter);


//        binding.fabAdd.setOnClickListener(view1 -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            View viewAdd = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_users,null);
//            builder.setView(viewAdd);
//            AlertDialog dialog = builder.create();
//            EditText ed_Name = viewAdd.findViewById(R.id.ed_Name);
//            EditText ed_idUser = viewAdd.findViewById(R.id.ed_idUser);
//            EditText ed_Number = viewAdd.findViewById(R.id.ed_Number);
//            Button btnAddLS = viewAdd.findViewById(R.id.btnAddUser);
//            Button btnCancelLS = viewAdd.findViewById(R.id.btnCancelUser);
//            btnCancelLS.setOnClickListener(view2 -> {
//                dialog.dismiss();
//            });
//            btnAddLS.setOnClickListener(view2 -> {
//                if(ed_Name.getText().toString().isEmpty()){
//                    Toast.makeText(context, "Bạn vui lòng không để trống!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                String name = ed_Name.getText().toString();
//                String id = ed_idUser.getText().toString();
//                String num = ed_Number.getText().toString();
//                try{
//                    User u = new User();
//                    u.setName(name);
//                    u.setId_user(id);
//                    u.setNumber(num);
//                    //id la primary key nen khong duoc de trong
//                    db.userDAO().insert(u);
//                    User user = db.userDAO().getUserByname(name);
//                    list.add(user);
//                    adapter.notifyItemInserted(list.size()-1);
//                    Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                }catch (Exception e){
//                    Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
//                }
//            });
//            dialog.show();
//        });
        return binding.getRoot();
  }
}