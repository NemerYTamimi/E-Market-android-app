package com.example.project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.AsyncTasks.ConnectionAsyncTask;
import com.example.project.R;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {
    public static Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cont = findViewById(R.id.button);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Login.class);
                ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
                connectionAsyncTask.execute("https://api.jsonbin.io/b/5f144593c1edc4661759fac9");
            }
        });
        setProgress(false);
    }

    public  int createToest(String connecting) {
        Toast toast=Toast.makeText(MainActivity.this, connecting, Toast.LENGTH_SHORT);
        toast.show();
        return toast.getDuration();
    }

    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar)
                findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
