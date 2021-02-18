package com.example.project.UIFragments.sale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Adapters.goodsAdapter;
import com.example.project.AsyncTasks.ConnectionAsyncTask;
import com.example.project.Modules.Good;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class SaleFragment extends Fragment {
    public static View root = null;
    private SaleViewModel goodsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goodsViewModel = ViewModelProviders.of(this).get(SaleViewModel.class);
        root = inflater.inflate(R.layout.fragment_sale, container, false);
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
                fillGoods(ConnectionAsyncTask.data,SaleFragment.root);

            }
        });
        return root;
    }

    public static void fillGoods(List<Good> goods, View root) {
        if (goods != null) {
            List <Good>SaleGoods=new ArrayList<>();
            for(int i=0;i<goods.size();i++)
                if(goods.get(i).getRating()>=4)
                    SaleGoods.add(goods.get(i));
            RecyclerView recyclerView = root.findViewById(R.id.goodslist);
            recyclerView.removeAllViewsInLayout();
            recyclerView.removeAllViews();
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            goodsAdapter goodsAdapter =new goodsAdapter(SaleGoods);
            recyclerView.setAdapter(goodsAdapter);
        } else {
            goods = new ArrayList<>();
            Good good;
            good = new Good("No Data", "type", " description", "http://www.ajeforum.com/wp-content/uploads/2018/04/Error_Culture_Florent_Darrault2.jpg", 0, 0, 0, 0);
            goods.add(good);
            RecyclerView recyclerView = root.findViewById(R.id.goodslist);
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            recyclerView.setAdapter(new goodsAdapter(goods));
        }
    }


    public int createToest(String connecting) {
        Toast toast = Toast.makeText(getContext(), connecting, Toast.LENGTH_SHORT);
        toast.show();
        return toast.getDuration();
    }



}