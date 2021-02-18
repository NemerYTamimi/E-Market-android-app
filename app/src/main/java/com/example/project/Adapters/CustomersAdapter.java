package com.example.project.Adapters;

import android.app.Dialog;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.AsyncTasks.ConnectionAsyncTask;
import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.Modules.Good;
import com.example.project.R;
import com.example.project.Modules.User;
import com.example.project.Activities.UserUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder> {
    private List<User> users;
    private Dialog dialog;
    public static double totalcost=0;

    public CustomersAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        final View view = inflater.inflate(R.layout.list_customer_layout, parent, false);
        final CustomersAdapter.CustomerViewHolder customerViewHolder = new CustomersAdapter.CustomerViewHolder(view);
        dialog = new Dialog(parent.getContext());
        dialog.setContentView(R.layout.diolog_user_cart);
        customerViewHolder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                UserUI userUI=new UserUI();
                UserUI.thisUser=users.get(customerViewHolder.getAdapterPosition());
                        List<Good>l=fillArrayList(dialog);
                for(int i=0;i<l.size();i++)
                    UserUI.thisUser.setMycart(l.get(i));
                fillGoods(l, dialog);
            }
        });



        return customerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        String title = (users.get(position).getFirstName().concat(" "+users.get(position).getLastName()));
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder{
        LinearLayout item_layout;
        TextView textView;

        public CustomerViewHolder(final View view){
            super(view);
            item_layout = (LinearLayout) itemView.findViewById(R.id.item);
            textView = itemView.findViewById(R.id.itemtitle);
        }
    }
    private List<Good> fillArrayList(Dialog view) {
        totalcost=0;
        final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(view.getContext(), "Market_db", null, 1);
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

    public static void fillGoods(List<Good> goods, Dialog dialog) {
        if (goods != null) {
            RecyclerView recyclerView = dialog.findViewById(R.id.goodslist);
            recyclerView.removeAllViewsInLayout();
            recyclerView.removeAllViews();
            recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
            UserGoodsAdapter userGoodsAdapter = new UserGoodsAdapter(goods);
            TextView total=dialog.findViewById(R.id.total);
            setTotal(goods);
            total.setText("Total = "+totalcost);
            recyclerView.setAdapter(userGoodsAdapter);
        }
    }
    public static void setTotal(List<Good> goods){
        for(int i=0;i<goods.size();i++)
            totalcost+=goods.get(i).getPrice();
    }

}
