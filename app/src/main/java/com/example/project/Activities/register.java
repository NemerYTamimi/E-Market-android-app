package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.Modules.User;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    private int f1=0;
    private int f2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final TextView emailF=findViewById(R.id.email);
        final TextView lnameF=findViewById(R.id.lname);
        final TextView fnameF=findViewById(R.id.fname);
        final TextView phoneF=findViewById(R.id.phone);
        final TextView pass1F=findViewById(R.id.newpass);
        final TextView pass2F=findViewById(R.id.pass2);
        ImageView back =findViewById(R.id.back);
        final Spinner city=findViewById(R.id.city);
        final Spinner gender=findViewById(R.id.gender);
        Button signup=findViewById(R.id.signup);
        final DataBaseHelper dataBaseHelper =new DataBaseHelper(register.this,"Market_db",null,1);
        String [] cities= {"Jerusalem", "Jenin", "Tulkarm", "Nablus", "Qalqilya", "Salfit","Jericho", "Ramallah","Al-Bireh", "Bethlehem","Hebron"};
        List<String> SpinnerCity = (List<String>) Arrays.asList(cities);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, SpinnerCity);
        city.setAdapter(spinnerArrayAdapter);
        ArrayList<String> spinnerGender = new ArrayList<>();
        spinnerGender.add("Male");
        spinnerGender.add("Female");
        ArrayAdapter spinnerArrayAdapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, spinnerGender);
        gender.setAdapter(spinnerArrayAdapter1);
        ImageView show1=findViewById(R.id.show1);
        ImageView show2=findViewById(R.id.show2);
        pass1F.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pass2F.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f1==0)
                {pass1F.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    f1=1;}
                else
                {pass1F.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    f1=0;}

            }
        });
        show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f2==0)
                {pass2F.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    f2=1;}
                else
                {pass2F.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    f2=0;}

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back =new Intent(register.this, Login.class);
                User user=new User(emailF.getText().toString(),fnameF.getText().toString(),lnameF.getText().toString(),pass1F.getText().toString(),gender.getSelectedItem().toString().equals("Male"),phoneF.getText().toString(),city.getSelectedItem().toString());
                user.setGoods("");
                user.setFavGoods("");
                if(pass1F.getText().toString().equals(pass2F.getText().toString()))
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailF.getText().toString()).matches())
                        if (fnameF.getText().toString().length() > 3 && lnameF.getText().toString().length() > 3)
                            if (phoneF.getText().toString().length() == 8)
                                if (pass1F.getText().toString().length() >= 6 && isValidPassword(pass1F.getText().toString())) {
                                    dataBaseHelper.insertUser(user);
                                    Toast.makeText(register.this, "Thanks and welcome to our e-market app ", Toast.LENGTH_LONG).show();
                                    startActivity(back);
                                    finish();
                                }

                 Toast.makeText(register.this, "Uncorrect data  ", Toast.LENGTH_LONG).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back =new Intent(register.this, Login.class);
                startActivity(back);
                finish();
            }
        });
    }

    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }
}
