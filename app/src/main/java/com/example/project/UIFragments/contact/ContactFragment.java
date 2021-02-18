package com.example.project.UIFragments.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel =
                ViewModelProviders.of(this).get(ContactViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_contact, container, false);
        contactViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Button call = root.findViewById(R.id.call);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent dialIntent = new Intent();
                        dialIntent.setAction(Intent.ACTION_DIAL);
                        dialIntent.setData(Uri.parse("tel:0599000000"));
                        startActivity(dialIntent);
                    }
                });
                Button find = root.findViewById(R.id.find);
                find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent findIntent = new Intent();
                        findIntent.setAction(Intent.ACTION_VIEW);
                        findIntent.setData(Uri.parse("geo:31.957977,35.180124"));
                        startActivity(findIntent);
                    }
                });
                Button mail = root.findViewById(R.id.email);
                mail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent gmailIntent = new Intent();
                        gmailIntent.setAction(Intent.ACTION_SENDTO);
                        gmailIntent.setType("message/rfc822");
                        gmailIntent.setData(Uri.parse("mailto:"));
                        gmailIntent.putExtra(Intent.EXTRA_EMAIL, "supermarket@super.com");
                        startActivity(gmailIntent);
                    }
                });
            }
        });
        return root;
    }
}