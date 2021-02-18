package com.example.project.UIFragments.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.Activities.Login;
import com.example.project.R;
import com.example.project.Activities.UserUI;

public class LogoutFragment extends Fragment {

    private LogoutViewModel logoutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutViewModel =
                ViewModelProviders.of(this).get(LogoutViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_logout, container, false);
        logoutViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Intent intent=new Intent(getContext(), Login.class);
                startActivity(intent);
                getActivity().finish();
                UserUI.thisUser=null;

            }
        });
        return root;
    }
}