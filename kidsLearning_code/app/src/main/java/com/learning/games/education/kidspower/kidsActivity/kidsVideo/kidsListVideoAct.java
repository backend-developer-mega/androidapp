package com.learning.games.education.kidspower.kidsActivity.kidsVideo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsAdapter.kidsVideoListAdapter;
import com.learning.games.education.kidspower.kidsDatabase.kidsDatabaseHelper;
import com.learning.games.education.kidspower.kidsModel.kidsModelVideo;

import java.util.ArrayList;

public class kidsListVideoAct extends AppCompatActivity {
    ArrayList<kidsModelVideo> arrOfVideoList;
    Context context;
    kidsDatabaseHelper databaseHelper;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    RecyclerView rvVideoList;
    kidsVideoListAdapter videoListAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_list_video);
        getSupportActionBar().hide();
        this.context = this;
        initDefine();
    }

    private void initDefine() {
        this.rvVideoList = (RecyclerView) findViewById(R.id.rvVideoList);
        ((TextView) findViewById(R.id.txtTitleSubHome)).setText(getIntent().getStringExtra("Category"));
        try {
            setRvVideoListAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        
    }

    private void setRvVideoListAdapter() {
        try {
            kidsDatabaseHelper databaseHelper2 = new kidsDatabaseHelper(this.context);
            this.databaseHelper = databaseHelper2;
            this.arrOfVideoList = databaseHelper2.getVideoDetails();
            this.rvVideoList.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            kidsVideoListAdapter videoListAdapter2 = new kidsVideoListAdapter(this.context, this.arrOfVideoList);
            this.videoListAdapter = videoListAdapter2;
            this.rvVideoList.setAdapter(videoListAdapter2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickBack(View view) {
        finish();
    }
}
