package com.learning.games.education.kidspower.kidsActivity.kidsVideo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;
import com.learning.games.education.kidspower.kidsModel.kidsModelVideo;
import java.util.ArrayList;

public class kidsVideoPlayAct extends YouTubeBaseActivity implements YouTubePlayer.OnFullscreenListener {
    int POSITION;
    ArrayList<kidsModelVideo> arrOfVideoList;
    Context context;
    boolean isFullScreen;
    YouTubePlayer player = null;
    RecyclerView rvVideoList;
    VideoAdapter videoAdapter;
    TextView videoTitleOfVideo;
    YouTubePlayerView youTubePlayerView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        this.context = this;
        initDefine();
    }

    private void initDefine() {
        this.rvVideoList = (RecyclerView) findViewById(R.id.rvVideoList);
        this.youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youTubePlayerView);
        this.videoTitleOfVideo = (TextView) findViewById(R.id.videoTitleOfVideo);
        Intent intent = getIntent();
        this.arrOfVideoList = (ArrayList) intent.getSerializableExtra("ArrayOfVideo");
        this.POSITION = intent.getIntExtra("Position", 0);
        setRvVideoAdapter();
        initVideoPlayer();
    }

    private void initVideoPlayer() {
        this.videoTitleOfVideo.setText(this.arrOfVideoList.get(this.POSITION).getVideoTitle());
        this.youTubePlayerView.initialize("AIzaSyDWYXoqet-qsx7obD5Aijj8-QmAA8Q2ZV0", new YouTubePlayer.OnInitializedListener() {
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    kidsVideoPlayAct.this.player = youTubePlayer;
                    kidsVideoPlayAct.this.player.cueVideo(kidsConstant.VIDEO_ID);
                }
            }

            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(kidsVideoPlayAct.this.context, "Failed To Load Video", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRvVideoAdapter() {
        this.rvVideoList.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        VideoAdapter videoAdapter2 = new VideoAdapter();
        this.videoAdapter = videoAdapter2;
        this.rvVideoList.setAdapter(videoAdapter2);
    }

    public void onFullscreen(boolean b) {
        this.isFullScreen = b;
    }

    class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
        VideoAdapter() {
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            ImageView imgThumbnail;
            TextView txtVideoTitle;

            ViewHolder(View view) {
                super(view);
                this.txtVideoTitle = (TextView) view.findViewById(R.id.txtVideoDescription);
                this.cardView = (CardView) view.findViewById(R.id.cardViewMain);
                this.imgThumbnail = (ImageView) view.findViewById(R.id.ivThumbnailView);
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(kidsVideoPlayAct.this.context).inflate(R.layout.card_item_related_list, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
            viewHolder.txtVideoTitle.setText(kidsVideoPlayAct.this.arrOfVideoList.get(i).getVideoTitle());
            Glide.with(kidsVideoPlayAct.this.context).load(kidsVideoPlayAct.this.arrOfVideoList.get(i).getVideoThumb()).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgThumbnail);
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    kidsVideoPlayAct.this.POSITION = i;
                    kidsConstant.VIDEO_ID = kidsVideoPlayAct.this.arrOfVideoList.get(i).getVideoId();
                    if (kidsVideoPlayAct.this.player != null) {
                        kidsVideoPlayAct.this.player.cueVideo(kidsConstant.VIDEO_ID);
                        kidsVideoPlayAct.this.videoTitleOfVideo.setText(kidsVideoPlayAct.this.arrOfVideoList.get(kidsVideoPlayAct.this.POSITION).getVideoTitle());
                    }
                }
            });
        }

        public int getItemCount() {
            return kidsVideoPlayAct.this.arrOfVideoList.size();
        }
    }
}
