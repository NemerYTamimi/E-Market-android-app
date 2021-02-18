package com.example.project.Adapters;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.AsyncTasks.DawnloadImageTask;
import com.example.project.Modules.Good;
import com.example.project.R;
import com.example.project.Activities.UserUI;

import java.util.List;

public class UserGoodsAdapter extends RecyclerView.Adapter<UserGoodsAdapter.Adapter1ViewHolder> {

    private List<Good> goods;
    private Dialog dialog;

    public UserGoodsAdapter(List<Good> goods) {
        this.goods = goods;
    }

    @NonNull
    @Override
    public Adapter1ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        final View view = inflater.inflate(R.layout.list_goods_layout, parent, false);
        final Adapter1ViewHolder adapter1ViewHolder = new Adapter1ViewHolder(view);

        return adapter1ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1ViewHolder holder, int position) {
        String title = goods.get(position).getTitle();
        holder.textView.setText(title);
        new DawnloadImageTask(holder.imageView, holder.progressBar).execute(goods.get(position).getImageurl());
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public class Adapter1ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout item_layout;
        TextView textView;
        ProgressBar progressBar;

        public Adapter1ViewHolder(View itemView) {
            super((itemView));
            item_layout = (LinearLayout) itemView.findViewById(R.id.item);
            imageView = itemView.findViewById(R.id.itemimage);
            textView = itemView.findViewById(R.id.itemtitle);
            progressBar = itemView.findViewById(R.id.progressBar3);
        }
    }

    private boolean test(Good good) {
            for (int i = 0; i < UserUI.thisUser.getMyfav().size(); i++)
                if (UserUI.thisUser.getMyfav().get(i).getTitle().equals(good.getTitle()))
                    return true;
        return false;
    }
    private boolean test1(Good good) {
        for (int i = 0; i < UserUI.thisUser.getMycart().size(); i++)
            if (UserUI.thisUser.getMycart().get(i).getTitle().equals(good.getTitle()))
                return true;
        return false;
    }
}
