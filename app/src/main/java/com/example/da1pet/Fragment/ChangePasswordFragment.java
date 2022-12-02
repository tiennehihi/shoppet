package com.example.da1pet.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.User;
import com.example.da1pet.Model.UserViewModel;
import com.example.da1pet.R;
import com.example.da1pet.databinding.FragmentChangePasswordBinding;

import java.util.ArrayList;


public class ChangePasswordFragment extends Fragment {
    FragmentChangePasswordBinding binding;
    ArrayList<User> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password, container, false);
        UserViewModel model = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        DbRoom db = DbRoom.getInstance(getActivity());
        list = (ArrayList<User>) db.userDAO().getUserByID(getActivity().getIntent().getStringExtra("username"));
        User user = list.get(0);
        binding.btnSave.setOnClickListener(view -> {
            if (checkValid()) {
                String oldPass = binding.edPassOld.getEditText().getText().toString();
                String newPass = binding.edPassChange.getEditText().getText().toString();
                String reNewPass = binding.edRePassChange.getEditText().getText().toString();
                if (!newPass.equals(reNewPass)) {
                    binding.edRePassChange.setError("Mật khẩu mới không trùng khớp!");
                    return;
                }
                binding.edRePassChange.setError("");

                if (!oldPass.equals(user.getPassword())) {
                    binding.edPassOld.setError("Mật khẩu không chính xác!");
                    return;
                }
                binding.edPassOld.setError("");
                user.setPassword(newPass);

                try {
                    db.userDAO().doipass(newPass, getActivity().getIntent().getStringExtra("username"));
                    model.setUser(user);
                    Toast.makeText(getActivity(), "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Đổi mật khẩu thất bại!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return binding.getRoot();
    }

    private boolean checkValid() {
        String oldPass = binding.edPassOld.getEditText().getText().toString();
        if (oldPass.isEmpty()) {
            binding.edPassOld.setError("Vui lòng không để trống!");
            return false;
        }
        binding.edPassOld.setError("");

        String newPass = binding.edPassChange.getEditText().getText().toString();
        if (newPass.isEmpty()) {
            binding.edPassChange.setError("Vui lòng không để trống!");
            return false;
        }
        binding.edPassChange.setError("");

        String reNewPass = binding.edRePassChange.getEditText().getText().toString();
        if (reNewPass.isEmpty()) {
            binding.edRePassChange.setError("Vui lòng không để trống!");
            return false;
        }
        binding.edRePassChange.setError("");
        return true;
    }
}