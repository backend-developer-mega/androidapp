package com.learning.games.education.kidspower.kidsAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsActivity.kidsVideo.kidsListVideoAct;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;

public class kidsVideoCategoryAdapter extends RecyclerView.Adapter<kidsVideoCategoryAdapter.ViewHolder> {
    Context context;
    int[] tumbnailList;
    String[] videocategory;

    public kidsVideoCategoryAdapter(Context context2, String[] videocategory2, int[] tumbnailList2) {
        this.context = context2;
        this.videocategory = videocategory2;
        this.tumbnailList = tumbnailList2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.card_list_home, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        Glide.with(this.context).load(Integer.valueOf(this.tumbnailList[i])).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgHomeCategory);
        viewHolder.lloutHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                kidsConstant.VIDEO_CATEGORY_ID = String.valueOf(i);
                kidsVideoCategoryAdapter.this.context.startActivity(new Intent(kidsVideoCategoryAdapter.this.context, kidsListVideoAct.class).putExtra("Category", kidsVideoCategoryAdapter.this.videocategory[i]));
            }
        });
    }

    public int getItemCount() {
        return this.videocategory.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomeCategory;
        LinearLayout lloutHome;

        public ViewHolder(View view) {
            super(view);
            this.imgHomeCategory = (ImageView) view.findViewById(R.id.imgHomeCategory);
            this.lloutHome = (LinearLayout) view.findViewById(R.id.lloutCardHome);
        }
    }
}
