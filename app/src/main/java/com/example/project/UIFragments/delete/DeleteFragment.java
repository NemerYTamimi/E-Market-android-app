package com.example.project.UIFragments.delete;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.R;
import com.example.project.Modules.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DeleteFragment extends Fragment {

    private DeleteViewModel deleteViewModel;
    private Dialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deleteViewModel =
                ViewModelProviders.of(this).get(DeleteViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_delete, container, false);
        deleteViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {
                final DataBaseHelper dataBaseHelper =new DataBaseHelper(getContext(),"Market_db",null,1);
                Cursor users=dataBaseHelper.getAllUsers();
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_accept);
                final Button yes=dialog.findViewById(R.id.yes);
                final Button no=dialog.findViewById(R.id.no);

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
                LinearLayout linearLayout=root.findViewById(R.id.mainLayout);
                linearLayout.removeAllViews();
                int x;
                for ( int i=0;i<users1.size();i++)
                {
                    final TextView cutomername=new TextView(getContext());
                    final TextView customeremail=new TextView(getContext());
                    final TextView cutomergender=new TextView(getContext());
                    final TextView customercity=new TextView(getContext());
                    final TextView cutomerphone=new TextView(getContext());
                    cutomername.setText(users1.get(i).getFirstName()+" "+users1.get(i).getLastName()+"   ");
                    cutomerphone.setText("+9705"+users1.get(i).getPhoneNumber());
                    customercity.setText(users1.get(i).getCity());
                    String gender;
                    if(users1.get(i).isMale())
                        gender="Male";
                    else
                        gender="Female";
                    cutomergender.setText(gender);
                    cutomername.setTextSize(24);
                    customeremail.setText(users1.get(i).getEmail());
                    customeremail.setTextSize(18);
                    cutomername.setPadding(20,20,0,20);
                    customeremail.setPadding(20,20,0,20);
                    customercity.setPadding(20,20,0,20);
                    cutomergender.setPadding(20,20,0,20);
                    cutomerphone.setPadding(20,20,0,20);
                    customeremail.setTextColor(getResources().getColor(R.color.black_overlay));
                    cutomername.setTextColor(getResources().getColor(R.color.black_overlay));
                    cutomergender.setTextColor(getResources().getColor(R.color.black_overlay));
                    cutomerphone.setTextColor(getResources().getColor(R.color.black_overlay));
                    customercity.setTextColor(getResources().getColor(R.color.black_overlay));
                    LinearLayout linearLayout1=new LinearLayout(getContext());
                    Space space=new Space(getContext());
                    Space space1=root.findViewById(R.id.space1);
                    space.setLayoutParams(space1.getLayoutParams());
                    linearLayout1.setBackground(getResources().getDrawable(R.drawable.black));
                    linearLayout1.setOrientation(LinearLayout.VERTICAL);
                    linearLayout1.addView(cutomername);
                    linearLayout1.addView(customeremail);
                    linearLayout1.addView(cutomergender);
                    linearLayout1.addView(customercity);
                    linearLayout1.addView(cutomerphone);
                    linearLayout.addView(space);
                    linearLayout.addView(linearLayout1);
                    x=i;
                    linearLayout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.show();
                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dataBaseHelper.deleteUser(customeremail.getText().toString());
                                    Snackbar.make(getView(), "Deleted successfully", Snackbar.LENGTH_LONG).show();
                                    dialog.hide();
                                    onChanged(s);
                                }
                            });
                           no.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   dialog.hide();
                               }
                           });
                        }
                    });
                }
            }
        });
        return root;
    }
}