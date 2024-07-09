package com.learning.games.education.kidspower.kidsAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsActivity.kidsFullScreenAct;
import com.learning.games.education.kidspower.kidsModel.kidsLearningDataModel;

import java.util.ArrayList;

public class kidsSubHomeAdapter extends RecyclerView.Adapter<kidsSubHomeAdapter.ViewHolder> {
    int TYPE;
    int unitType;
    Context context;
    ArrayList<kidsLearningDataModel> learningDataModelArrayList;
    int p;
    String category;

    public kidsSubHomeAdapter(Context context2, ArrayList<kidsLearningDataModel> learningDataModelArrayList2, int i, int position, String category) {
        this.context = context2;
        this.learningDataModelArrayList = learningDataModelArrayList2;
        this.p = i;
        this.TYPE = position;
        this.category = category;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgSubHome;

        ViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.cvCardSubHome);
            this.imgSubHome = (ImageView) view.findViewById(R.id.imgSubHome);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_sub_list_home, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        Glide.with(this.context).load(Integer.valueOf(this.learningDataModelArrayList.get(i).image)).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgSubHome);
        viewHolder.cardView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.bubble_anim));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(kidsSubHomeAdapter.this.context, kidsFullScreenAct.class);
                intent.putExtra("categoryPosition", kidsSubHomeAdapter.this.p);
                intent.putExtra("selectedPosition", i);
                intent.putExtra("category", kidsSubHomeAdapter.this.category);
                kidsSubHomeAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.learningDataModelArrayList.size();
    }
}
