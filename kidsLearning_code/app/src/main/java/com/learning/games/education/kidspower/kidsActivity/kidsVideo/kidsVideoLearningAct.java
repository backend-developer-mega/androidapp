package com.learning.games.education.kidspower.kidsActivity.kidsVideo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsAdapter.kidsVideoCategoryAdapter;

public class kidsVideoLearningAct extends AppCompatActivity {
    Context context;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    RecyclerView rvVideoCategory;
    int[] tumbnailList;
    kidsVideoCategoryAdapter videoCategoryAdapter;
    String[] videocategory;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_video_learning);
        this.context = this;
        getSupportActionBar().hide();
        initDefine();
        this.videocategory = new String[]{"ABC Songs", "Number Songs", "Color Songs", "Animal Songs", "Shape Songs", "Vehicle Songs", "Fruit Songs", "Vegetable Songs", "Day Songs", "Month Songs", "Clothes Songs"};
        this.tumbnailList = new int[]{R.drawable.vt_abc, R.drawable.vt_number, R.drawable.vt_color, R.drawable.vt_animal, R.drawable.vt_shape, R.drawable.vt_vehicle, R.drawable.vt_fruit, R.drawable.vt_vegetable, R.drawable.vt_day, R.drawable.vt_month, R.drawable.vt_clothes};
        setRvAdapter();
      
        
    }

    private void initDefine() {
        this.rvVideoCategory = (RecyclerView) findViewById(R.id.rvVideoCategory);
    }

    private void setRvAdapter() {
        this.rvVideoCategory.setLayoutManager(new GridLayoutManager(this.context, 2, RecyclerView.VERTICAL, false));
        kidsVideoCategoryAdapter videoCategoryAdapter2 = new kidsVideoCategoryAdapter(this.context, this.videocategory, this.tumbnailList);
        this.videoCategoryAdapter = videoCategoryAdapter2;
        this.rvVideoCategory.setAdapter(videoCategoryAdapter2);
    }

    public void onClickBack(View view) {
        finish();
    }
}
