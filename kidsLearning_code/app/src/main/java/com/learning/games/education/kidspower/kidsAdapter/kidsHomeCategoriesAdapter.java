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
import com.learning.games.education.kidspower.kidsActivity.kidsExam.kidsListenGuessAct;
import com.learning.games.education.kidspower.kidsActivity.kidsExam.kidsLookChooseAct;
import com.learning.games.education.kidspower.kidsActivity.kidsSubAct;

public class kidsHomeCategoriesAdapter extends RecyclerView.Adapter<kidsHomeCategoriesAdapter.ViewHolder> {
    int TYPE;
    Context context;
    String[] homeCategoryTitles;
    int[] mainCategoryList;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomeCategory;
        LinearLayout lloutHome;

        ViewHolder(View view) {
            super(view);
            this.imgHomeCategory = (ImageView) view.findViewById(R.id.imgHomeCategory);
            this.lloutHome = (LinearLayout) view.findViewById(R.id.lloutCardHome);
        }
    }

    public kidsHomeCategoriesAdapter(Context context2, int[] mainCategoryList2, String[] homeCategoryTitles2, int type) {
        this.context = context2;
        this.mainCategoryList = mainCategoryList2;
        this.homeCategoryTitles = homeCategoryTitles2;
        this.TYPE = type;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.card_list_home, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        Glide.with(this.context).load(Integer.valueOf(this.mainCategoryList[i])).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgHomeCategory);
        viewHolder.lloutHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (kidsHomeCategoriesAdapter.this.TYPE == 1) {
                    Intent intent = new Intent(kidsHomeCategoriesAdapter.this.context, kidsSubAct.class);
                    intent.putExtra("categoryPosition", i);
                    intent.putExtra("Category", kidsHomeCategoriesAdapter.this.homeCategoryTitles[i]);
                    intent.putExtra("Type", kidsHomeCategoriesAdapter.this.TYPE);
                    kidsHomeCategoriesAdapter.this.context.startActivity(intent);
                } else if (kidsHomeCategoriesAdapter.this.TYPE == 2) {
                    Intent intent2 = new Intent(kidsHomeCategoriesAdapter.this.context, kidsLookChooseAct.class);
                    intent2.putExtra("categoryPosition", i);
                    intent2.putExtra("SubCate", kidsHomeCategoriesAdapter.this.homeCategoryTitles[i]);
                    intent2.putExtra("TYPE", 2);
                    kidsHomeCategoriesAdapter.this.context.startActivity(intent2);
                } else if (kidsHomeCategoriesAdapter.this.TYPE == 3) {
                    Intent intent3 = new Intent(kidsHomeCategoriesAdapter.this.context, kidsListenGuessAct.class);
                    intent3.putExtra("categoryPosition", i);
                    intent3.putExtra("SubCate", kidsHomeCategoriesAdapter.this.homeCategoryTitles[i]);
                    intent3.putExtra("TYPE", 3);
                    kidsHomeCategoriesAdapter.this.context.startActivity(intent3);
                }
            }
        });
    }

    public int getItemCount() {
        return this.mainCategoryList.length;
    }
}
