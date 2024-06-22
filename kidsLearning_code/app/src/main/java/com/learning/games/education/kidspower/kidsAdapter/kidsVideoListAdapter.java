package com.learning.games.education.kidspower.kidsAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsActivity.kidsVideo.kidsVideoPlayAct;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;
import com.learning.games.education.kidspower.kidsModel.kidsModelVideo;
import java.util.ArrayList;

public class kidsVideoListAdapter extends RecyclerView.Adapter<kidsVideoListAdapter.ViewHolder> {
    ArrayList<kidsModelVideo> arrOfVideoList;
    Context context;

    public kidsVideoListAdapter(Context context2, ArrayList<kidsModelVideo> arrOfVideoList2) {
        this.context = context2;
        this.arrOfVideoList = arrOfVideoList2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.card_item_video_list, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        Glide.with(this.context).load(this.arrOfVideoList.get(i).getVideoThumb()).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgThumbnail);
        viewHolder.txtVideoTitle.setText(this.arrOfVideoList.get(i).getVideoTitle());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                kidsConstant.VIDEO_ID = kidsVideoListAdapter.this.arrOfVideoList.get(i).getVideoId();
                kidsVideoListAdapter.this.context.startActivity(new Intent(kidsVideoListAdapter.this.context, kidsVideoPlayAct.class).putExtra("Position", i).putExtra("ArrayOfVideo", kidsVideoListAdapter.this.arrOfVideoList));
            }
        });
    }

    public int getItemCount() {
        return this.arrOfVideoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgThumbnail;
        TextView txtVideoTitle;

        public ViewHolder(View view) {
            super(view);
            this.txtVideoTitle = (TextView) view.findViewById(R.id.txtVideoDescription);
            this.cardView = (CardView) view.findViewById(R.id.cardViewMain);
            this.imgThumbnail = (ImageView) view.findViewById(R.id.ivThumbnailView);
        }
    }
}
