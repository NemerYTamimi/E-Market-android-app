package com.example.project.Adapters;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.DataBaseHelpers.DataBaseHelper;
import com.example.project.AsyncTasks.DawnloadImageTask;
import com.example.project.Modules.Good;
import com.example.project.R;
import com.example.project.Activities.UserUI;
import com.example.project.UIFragments.fav.favFragment;

import java.util.List;

public class goodsAdapter extends RecyclerView.Adapter<goodsAdapter.Adapter1ViewHolder> {

    private List<Good> goods;
    private Dialog dialog;

    public goodsAdapter(List<Good> goods) {
        this.goods = goods;
    }

    @NonNull
    @Override
    public Adapter1ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        final View view = inflater.inflate(R.layout.list_goods_layout, parent, false);
        final Adapter1ViewHolder adapter1ViewHolder = new Adapter1ViewHolder(view);
        dialog = new Dialog(parent.getContext());
        dialog.setContentView(R.layout.dialog_item);
        adapter1ViewHolder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView type = dialog.findViewById(R.id.dialog_typeView);
                TextView title = dialog.findViewById(R.id.dialog_titleView);
                ImageView image = dialog.findViewById(R.id.dialog_imageView);
                TextView rating = dialog.findViewById(R.id.dialog_ratingView);
                TextView description = dialog.findViewById(R.id.dialog_descriptionView);
                TextView heigh = dialog.findViewById(R.id.dialog_heighView);
                TextView width = dialog.findViewById(R.id.dialog_widthView);
                TextView price = dialog.findViewById(R.id.dialog_priceView);
                final ImageButton favbtn = dialog.findViewById(R.id.favbtn);
                ProgressBar progressBar = dialog.findViewById(R.id.progressBar4);
                if (test(goods.get(adapter1ViewHolder.getAdapterPosition())))
                    favbtn.setBackgroundResource(R.drawable.icons8_unpin_50px);
                if (!test(goods.get(adapter1ViewHolder.getAdapterPosition())))
                    favbtn.setBackgroundResource(R.drawable.icons8_add_to_favorites_50px_3);
                price.setText(String.valueOf(goods.get(adapter1ViewHolder.getAdapterPosition()).getPrice()));
                width.setText(String.valueOf(goods.get(adapter1ViewHolder.getAdapterPosition()).getWidth()));
                heigh.setText(String.valueOf(goods.get(adapter1ViewHolder.getAdapterPosition()).getHeight()));
                rating.setText(String.valueOf(goods.get(adapter1ViewHolder.getAdapterPosition()).getRating()));
                title.setText(goods.get(adapter1ViewHolder.getAdapterPosition()).getTitle());
                type.setText(goods.get(adapter1ViewHolder.getAdapterPosition()).getType());
                description.setText(goods.get(adapter1ViewHolder.getAdapterPosition()).getDescription());
                new DawnloadImageTask(image, progressBar).execute(goods.get(adapter1ViewHolder.getAdapterPosition()).getImageurl());
                final ImageButton add = dialog.findViewById(R.id.cortbtn);
                add.setBackgroundResource(R.drawable.icons8_add_shopping_cart_50px);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog1 = new Dialog(parent.getContext());
                        dialog1.setContentView(R.layout.dialog_add_to_cart);
                        TextView amount =dialog1.findViewById(R.id.amount);
                        Button addAmount =dialog1.findViewById(R.id.yes);
                        addAmount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (!test1(goods.get(adapter1ViewHolder.getAdapterPosition()))) {
                                    UserUI.thisUser.setMycart(goods.get(adapter1ViewHolder.getAdapterPosition()));
                                    if(UserUI.thisUser.getGoods().equals("")) {
                                        UserUI.thisUser.setGoods(goods.get(adapter1ViewHolder.getAdapterPosition()).getTitle());
                                        final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(parent.getContext(), "Market_db", null, 1);
                                        dataBaseHelper1.updateUser(UserUI.thisUser);
                                    }
                                    else {
                                        UserUI.thisUser.setGoods(UserUI.thisUser.getGoods().concat(","+goods.get(adapter1ViewHolder.getAdapterPosition()).getTitle()));
                                        final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(parent.getContext(), "Market_db", null, 1);
                                        dataBaseHelper1.updateUser(UserUI.thisUser);
                                    }
                                }
                            }
                        });
                        dialog1.show();
                    }
                });
                favbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!test(goods.get(adapter1ViewHolder.getAdapterPosition()))) {
                            favbtn.setBackgroundResource(R.drawable.icons8_unpin_50px);
                            UserUI.thisUser.setMyfav(goods.get(adapter1ViewHolder.getAdapterPosition()));
                            if(UserUI.thisUser.getFavGoods().equals("")) {
                                UserUI.thisUser.setFavGoods(goods.get(adapter1ViewHolder.getAdapterPosition()).getTitle());
                                final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(parent.getContext(), "Market_db", null, 1);
                                dataBaseHelper1.updateUser(UserUI.thisUser);
                            }
                            else {
                                UserUI.thisUser.setFavGoods(UserUI.thisUser.getFavGoods().concat(","+goods.get(adapter1ViewHolder.getAdapterPosition()).getTitle()));
                                final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(parent.getContext(), "Market_db", null, 1);
                                dataBaseHelper1.updateUser(UserUI.thisUser);
                            }
                            }
                        else {
                            int index = 0;
                            favbtn.setBackgroundResource(R.drawable.icons8_add_to_favorites_50px_3);
                            for (int i = 0; i < UserUI.thisUser.getMyfav().size(); i++)
                                if (UserUI.thisUser.getMyfav().get(i).toString().equals(goods.get(adapter1ViewHolder.getAdapterPosition()).toString()))
                                    index = i;
                            UserUI.thisUser.setFavGoods(UserUI.thisUser.getFavGoods().replace(goods.get(adapter1ViewHolder.getAdapterPosition()).getTitle(),""));
                            UserUI.thisUser.getMyfav().remove(index);
                            final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(parent.getContext(), "Market_db", null, 1);
                            dataBaseHelper1.updateUser(UserUI.thisUser);
                            dialog.hide();
                            favFragment.fillGoods(UserUI.thisUser.getMyfav(), favFragment.root);
                        }
                    }
                }
                );
                dialog.show();
            }
        });
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
