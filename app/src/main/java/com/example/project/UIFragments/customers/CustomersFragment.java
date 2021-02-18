package com.example.project.UIFragments.customers;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Adapters.CustomersAdapter;
import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.R;
import com.example.project.Modules.User;

import java.util.ArrayList;
import java.util.List;

public class CustomersFragment extends Fragment {

    private CustomersViewModel customersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        customersViewModel =
                ViewModelProviders.of(this).get(CustomersViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_showcustomers, container, false);
        customersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                final DataBaseHelper dataBaseHelper =new DataBaseHelper(getContext(),"Market_db",null,1);
                Cursor users=dataBaseHelper.getAllUsers();
                final List<User> users1=new ArrayList<>();
                while (users.moveToNext())
                {
                    User user=new User();
                    user.setEmail(users.getString(0));
                    user.setFirstName(users.getString(3));
                    user.setLastName(users.getString(4));
                    user.setPassword(users.getString(6));//3
                    user.setGender(users.getString(5).equals("1"));
                    user.setPhoneNumber(users.getString(2));
                    user.setCity(users.getString(1));
                    user.setGoods(users.getString(7));
                    user.setFavGoods(users.getString(8));
                    users1.add(user);
                }
                RecyclerView recyclerView=root.findViewById(R.id.cutomerlist);
                recyclerView.removeAllViewsInLayout();
                recyclerView.removeAllViews();
                recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
                CustomersAdapter customersAdapter = new CustomersAdapter(users1);
                recyclerView.setAdapter(customersAdapter);
            }
        });
        return root;
    }
}