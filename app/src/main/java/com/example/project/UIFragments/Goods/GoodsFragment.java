package com.example.project.UIFragments.Goods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class GoodsFragment extends Fragment {
    public static View root = null;
    private GoodsViewModel goodsViewModel;

    public static void fillGoods(List<Good> goods, View root) {
        if (goods != null) {
            RecyclerView recyclerView = root.findViewById(R.id.goodslist);
            recyclerView.removeAllViewsInLayout();
            recyclerView.removeAllViews();
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            goodsAdapter goodsAdapter = new goodsAdapter(goods);
            recyclerView.setAdapter(goodsAdapter);
        }
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goodsViewModel = ViewModelProviders.of(this).get(GoodsViewModel.class);
        root = inflater.inflate(R.layout.fragment_goods, container, false);
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
                fillGoods(ConnectionAsyncTask.data, root);
            }
        });
        Button filterbtn = root.findViewById(R.id.filterbtn);
        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter();
            }
        });
        return root;

    }

    public void filter() {
        EditText price = root.findViewById(R.id.price);
        EditText heighf = root.findViewById(R.id.Height_from);
        EditText height = root.findViewById(R.id.Height_to);
        List<Good> filteredList = new ArrayList<>();

        if (price.getText() != null && !price.getText().toString().equals("")) {
            for (int i = 0; i < ConnectionAsyncTask.data.size(); i++) {
                if (ConnectionAsyncTask.data.get(i).getPrice() <= Double.valueOf(price.getText().toString())) {
                    filteredList.add(ConnectionAsyncTask.data.get(i));
                }
            }
        } else
            filteredList = ConnectionAsyncTask.data;
        List<Good> filteredList1 = new ArrayList<>();
        if (heighf.getText() != null && height.getText() != null && !heighf.getText().toString().equals("") && !height.getText().toString().equals(""))
            for (int i = 0; i < filteredList.size(); i++) {
                if (filteredList.get(i).getHeight() >= Integer.valueOf(heighf.getText().toString()) && filteredList.get(i).getHeight() <= Integer.valueOf(height.getText().toString())) {
                    filteredList1.add(filteredList.get(i));
                }
            }
        else
            filteredList1 = filteredList;
        List<Good> filteredList2 = new ArrayList<>();
        Spinner types = root.findViewById(R.id.Types);
        if (!types.getSelectedItem().toString().equals("select type"))
            for (int i = 0; i < filteredList1.size(); i++) {
                if (filteredList1.get(i).getType().equalsIgnoreCase(types.getSelectedItem().toString()))
                    filteredList2.add(filteredList1.get(i));
            }
        else
            filteredList2 = filteredList1;
        fillGoods(filteredList2, root);
    }

    public int createToest(String connecting) {
        Toast toast = Toast.makeText(getContext(), connecting, Toast.LENGTH_SHORT);
        toast.show();
        return toast.getDuration();
    }

}