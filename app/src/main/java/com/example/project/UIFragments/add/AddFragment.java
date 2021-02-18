package com.example.project.UIFragments.add;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.Activities.AdminUI;
import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.R;
import com.example.project.Modules.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class AddFragment extends Fragment {
    private int f1 = 0;
    private int f2 = 0;
    private AddViewModel addViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                ViewModelProviders.of(this).get(AddViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_add, container, false);
        final TextView emailF = root.findViewById(R.id.email);
        final TextView lnameF = root.findViewById(R.id.lname);
        final TextView fnameF = root.findViewById(R.id.fname);
        final TextView phoneF = root.findViewById(R.id.phone);
        final TextView pass1F = root.findViewById(R.id.newpass);
        final TextView pass2F = root.findViewById(R.id.pass2);
        final Spinner city = root.findViewById(R.id.city);
        final Spinner gender = root.findViewById(R.id.gender);
        Button signup = root.findViewById(R.id.signup);
        final DataBaseHelper dataBaseHelper = new DataBaseHelper(root.getContext(), "Market_db", null, 1);
        String[] cities = {"Jerusalem", "Jenin", "Tulkarm", "Nablus", "Qalqilya", "Salfit", "Jericho", "Ramallah", "Al-Bireh", "Bethlehem", "Hebron"};
        List<String> SpinnerCity = (List<String>) Arrays.asList(cities);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(root.getContext(), android.R.layout.simple_spinner_dropdown_item, SpinnerCity);
        city.setAdapter(spinnerArrayAdapter);
        ArrayList<String> spinnerGender = new ArrayList<>();
        spinnerGender.add("Male");
        spinnerGender.add("Female");
        ArrayAdapter spinnerArrayAdapter1 = new ArrayAdapter(root.getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerGender);
        gender.setAdapter(spinnerArrayAdapter1);
        ImageView show1 = root.findViewById(R.id.show1);
        ImageView show2 = root.findViewById(R.id.show2);
        pass1F.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pass2F.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f1 == 0) {
                    pass1F.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    f1 = 1;
                } else {
                    pass1F.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    f1 = 0;
                }

            }
        });
        show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f2 == 0) {
                    pass2F.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    f2 = 1;
                } else {
                    pass2F.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    f2 = 0;
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(emailF.getText().toString(), fnameF.getText().toString(), lnameF.getText().toString(), pass1F.getText().toString(), gender.getSelectedItem().toString().equals("Male"), phoneF.getText().toString(), city.getSelectedItem().toString());
                if (pass1F.getText().toString().equals(pass2F.getText().toString()))
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailF.getText().toString()).matches())
                        if (fnameF.getText().toString().length() > 3 && lnameF.getText().toString().length() > 3)
                            if (phoneF.getText().toString().length() == 8)
                                if (pass1F.getText().toString().length() >= 6 && isValidPassword(pass1F.getText().toString())) {
                                    dataBaseHelper.insertAdmin(user);
                                    Snackbar.make(getView(), "New admin account added", Snackbar.LENGTH_LONG).show();
                                    return;
                                }
                Snackbar.make(getView(), "check", Snackbar.LENGTH_LONG).show();
            }
        });
        ImageView back =root.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back =new Intent(root.getContext(), AdminUI.class);
                startActivity(back);
                getActivity().finish();
            }
        });
        return root;
    }
    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }
}