package com.example.project.UIFragments.cart;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Adapters.goodsAdapter;
import com.example.project.AsyncTasks.ConnectionAsyncTask;
import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.Modules.Good;
import com.example.project.R;
import com.example.project.Activities.UserUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartFragment extends Fragment {
    public static View root = null;
    private CartViewModel goodsViewModel;
    public static double totalcost=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goodsViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        root = inflater.inflate(R.layout.fragment_cart, container, false);
//        RecyclerView recyclerView = root.findViewById(R.id.goodslist);
//        recyclerView.addOnItemTouchListener(new ItemOnClickListener(getContext(), recyclerView ,new ItemOnClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        // do whatever
//                    }
//
//                    @Override public void onLongItemClick(View view, int position) {
//                        // do whatever
//                    }
//                });
        goodsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                List<Good> l=fillArrayList();
                for(int i=0;i<l.size();i++)
                    UserUI.thisUser.setMycart(l.get(i));
                fillGoods(l, root);
            }
        });
        return root;
    }

    private List<Good> fillArrayList() {
        totalcost=0;
        final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(getContext(), "Market_db", null, 1);
        Cursor users = dataBaseHelper1.getAllUsers();
        String goodsString = null;
        while (users.moveToNext()) {
            if (users.getString(0).equals(UserUI.thisUser.getEmail())) {
                goodsString = users.getString(7);
                break;
            }
        }
        String[] goodsArray = goodsString.split(",");
        List<String> goodsTiles = Arrays.asList(goodsArray);
        List<Good> goods = new ArrayList<>();
        for (int i = 0; i < ConnectionAsyncTask.data.size(); i++)
            for (int j = 0; j < goodsTiles.size(); j++)
                if (ConnectionAsyncTask.data.get(i).getTitle().equals(goodsTiles.get(j))) {
                    goods.add(ConnectionAsyncTask.data.get(i));
                    continue;
                }
        return goods;
    }

    public static void fillGoods(List<Good> goods, View root) {
        if (goods != null) {
            RecyclerView recyclerView = root.findViewById(R.id.goodslist);
            recyclerView.removeAllViewsInLayout();
            recyclerView.removeAllViews();
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            goodsAdapter goodsAdapter = new goodsAdapter(goods);
            TextView total=root.findViewById(R.id.total);
            setTotal(goods);
            total.setText("Total = "+totalcost);
            recyclerView.setAdapter(goodsAdapter);
        }
    }

    public static void setTotal(List<Good> goods){
        for(int i=0;i<goods.size();i++)
            totalcost+=goods.get(i).getPrice();
    }


}