package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.Modules.User;
import com.example.project.R;
import com.example.project.SharedPrefManager.SharedPrefManager;

public class Login extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    private    int  f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DataBaseHelper dataBaseHelper1 =new DataBaseHelper(Login.this,"Market_db",null,1);
        setContentView(R.layout.activity_login);
        Button signin=findViewById(R.id.signin);
        sharedPrefManager =SharedPrefManager.getInstance(this);
        Button signup=findViewById(R.id.signup);
            final TextView emailF=findViewById(R.id.email);
        final TextView passF=findViewById(R.id.newpass);
        ImageView show =findViewById(R.id.show);
        User Admin =new User();
        Admin.setEmail("admin@market.com");
        Admin.setPassword("Sudo55$");
        dataBaseHelper1.insertAdmin(Admin);
        passF.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f==0)
                {passF.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                f=1;}
                else
                {passF.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                f=0;}

            }
        });
        String emailshp="";
        String passshp="";
        final Switch rememberme=findViewById(R.id.rem);
         emailshp=sharedPrefManager.readString("email","");
         passshp=sharedPrefManager.readString("password","");

        if(!emailshp.equals(""))
        {emailF.setText(emailshp);}
        if(!passshp.equals(""))
        {passF.setText(passshp);}
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginUser =new Intent(Login.this, UserUI.class);
                Intent loginAdmin =new Intent(Login.this, AdminUI.class);

                String email=emailF.getText().toString();
                String pass=passF.getText().toString();
                Cursor users =dataBaseHelper1.getAllUsers();
                int f=1;
                if(f==1)
                while (users.moveToNext())
                {
                    if (users.getString(0).equals(email)){
                        System.out.println(users.getString(0));
                        if(users.getString(6).equals(pass))
                        {
                            Toast.makeText(Login.this,"Welcome "+users.getString(3),Toast.LENGTH_SHORT).show();
                            startActivity(loginUser);
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
                            UserUI.thisUser=user;

                            if(rememberme.isChecked())
                            {sharedPrefManager.writeString("email",emailF.getText().toString());
                            sharedPrefManager.writeString("password",passF.getText().toString());}
                            else
                            {
                                sharedPrefManager.writeString("email","");
                                sharedPrefManager.writeString("password","");
                            }
                            f=0;
                        }
                    }
                }

                Cursor admins =dataBaseHelper1.getAllAdmins();
                if(f==1)
                while (admins.moveToNext())
                {
                    if (admins.getString(0).equals(email)){
                        if(admins.getString(6).equals(pass))
                        {

                            Toast.makeText(Login.this,"Welcome "+admins.getString(3),Toast.LENGTH_SHORT).show();
                            startActivity(loginAdmin);
                            finish();
                            if(rememberme.isChecked())
                            {sharedPrefManager.writeString("email",emailF.getText().toString());
                                sharedPrefManager.writeString("password",passF.getText().toString());}
                            else
                            {
                                sharedPrefManager.writeString("email","");
                                sharedPrefManager.writeString("password","");
                            }
                        }
                    }
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup =new Intent(Login.this, register.class);
                startActivity(signup);
                finish();
            }
        });
    }

}
