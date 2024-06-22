package com.learning.games.education.kidspower.kidsAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.learning.games.education.kidspower.R;

public class kidsHomeAdapter extends RecyclerView.Adapter<kidsHomeAdapter.ViewHolder> {
    int[] arrOfCategory;
    Context context;
    onClickMain onClickMain;

    public interface onClickMain {
        void onClickCategory(int i);
    }

    public kidsHomeAdapter(Context context2, int[] arrOfCategory2, onClickMain onClickMain2) {
        this.context = context2;
        this.arrOfCategory = arrOfCategory2;
        this.onClickMain = onClickMain2;
    }

    public int getItemCount() {
        return this.arrOfCategory.length;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.card_list_start, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        Glide.with(this.context).load(Integer.valueOf(this.arrOfCategory[i])).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgThumbnail);
        viewHolder.cVHomeCategories.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kidsHomeAdapter.this.onClickMain.onClickCategory(i);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cVHomeCategories;
        ImageView imgThumbnail;

        ViewHolder(View view) {
            super(view);
            this.cVHomeCategories = (CardView) view.findViewById(R.id.cVHomeCategories);
            this.imgThumbnail = (ImageView) view.findViewById(R.id.imgThumbnail);
        }
    }
}
