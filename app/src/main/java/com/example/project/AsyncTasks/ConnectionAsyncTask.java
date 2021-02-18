package com.example.project.AsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.project.Modules.Good;
import com.example.project.JsonTransfers.GoodJasonParser;
import com.example.project.HttpManagers.HttpManager;
import com.example.project.Activities.MainActivity;

import java.util.List;

public class ConnectionAsyncTask extends AsyncTask<String, String, String> {
    public static List<Good> data=null;
    Activity activity;
     int f=0;
    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ((MainActivity) activity).createToest("connecting");
        ((MainActivity) activity).setProgress(true);
    }

    @Override
    protected String doInBackground(String... params) {

        String data = null;
        try {
            data= HttpManager.getData(params[0]);
            f=1;
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        f=0;
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s!=null)
        {
            List<Good> goods = GoodJasonParser.getObjectFromJason(s);
            data = goods;
            ((MainActivity) activity).createToest("connected");
            ((MainActivity) activity).setProgress(false);
            ((MainActivity)activity).startActivity(MainActivity.intent);
            ((MainActivity)activity).finish();
        }
        }
    }