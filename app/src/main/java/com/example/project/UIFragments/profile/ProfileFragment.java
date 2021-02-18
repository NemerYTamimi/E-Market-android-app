package com.example.project.UIFragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.R;
import com.example.project.Modules.User;
import com.example.project.Activities.UserUI;
import com.example.project.Activities.register;
import com.example.project.UIFragments.sale.SaleViewModel;

public class ProfileFragment extends Fragment {

    private SaleViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(SaleViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                final TextView fname = root.findViewById(R.id.fname);
                final TextView lname = root.findViewById(R.id.lname);
                final TextView gender = root.findViewById(R.id.gender);
                final TextView phone = root.findViewById(R.id.phone);
                final TextView city = root.findViewById(R.id.city);
                final TextView oldpass = root.findViewById(R.id.oldPass);
                final TextView newpass = root.findViewById(R.id.newpass);
                fname.setText(UserUI.thisUser.getFirstName());
                lname.setText(UserUI.thisUser.getLastName());
                phone.setText(UserUI.thisUser.getPhoneNumber());
                city.setText(UserUI.thisUser.getCity());
                String genderString;
                if (UserUI.thisUser.isMale())
                    genderString = "Male";
                else
                    genderString = "Women";
                gender.setText(genderString);
                ImageButton modify = root.findViewById(R.id.modifybtn);
                modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();
                        if (fname.getText().toString().length() > 3 && lname.getText().toString().length() > 3) {
                            if (phone.getText().length() == 8) {
                                if (oldpass.getText().toString().equals(UserUI.thisUser.getPassword())) {
                                    if (newpass.getText().toString().isEmpty()) {
                                        user.setPassword(UserUI.thisUser.getPassword());
                                        user.setEmail(UserUI.thisUser.getEmail());
                                        user.setGoods(UserUI.thisUser.getGoods());
                                        user.setMale(UserUI.thisUser.isMale());
                                        user.setFirstName(fname.getText().toString());
                                        user.setLastName(lname.getText().toString());
                                        user.setCity(UserUI.thisUser.getCity());
                                        user.setFavGoods(UserUI.thisUser.getFavGoods());
                                        user.setPhoneNumber(UserUI.thisUser.getPhoneNumber());
                                        Toast.makeText(root.getContext(), "Profile updated successfuly", Toast.LENGTH_SHORT).show();

                                    } else if (register.isValidPassword(newpass.getText().toString())) {

                                            user.setPassword(newpass.getText().toString());
                                            user.setEmail(UserUI.thisUser.getEmail());
                                            user.setGoods(UserUI.thisUser.getGoods());
                                            user.setMale(UserUI.thisUser.isMale());
                                            user.setFirstName(fname.getText().toString());
                                            user.setLastName(lname.getText().toString());
                                            user.setCity(UserUI.thisUser.getCity());
                                            user.setFavGoods(UserUI.thisUser.getFavGoods());
                                            user.setPhoneNumber(phone.getText().toString());
                                            Toast.makeText(root.getContext(), "Profile updated successfuly", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                        Toast.makeText(root.getContext(), "Please enter a valid new pass or clear the filed to keep the old password", Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(getContext(), "Please enter a valid old password", Toast.LENGTH_SHORT).show();
                                }
                            } else
                                Toast.makeText(root.getContext(), "please enter a valid phone number", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(root.getContext(), "please enter a valid first name and last Name", Toast.LENGTH_SHORT).show();

                        final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(root.getContext(), "Market_db", null, 1);
                        dataBaseHelper1.updateUser(user);
                    }
                });
            }
        });
        return root;
    }
}