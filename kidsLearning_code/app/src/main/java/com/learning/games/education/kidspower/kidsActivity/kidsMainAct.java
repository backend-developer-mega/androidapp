package com.learning.games.education.kidspower.kidsActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsAdapter.kidsHomeAdapter;
import com.learning.games.education.kidspower.kidsCustom.kidsAppControl;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;
import com.learning.games.education.kidspower.kidsDatabase.kidsDatabaseHelper;
import com.learning.games.education.kidspower.kidsUtils.Utils;

public class kidsMainAct extends AppCompatActivity {
    public int[] arrOfCategory;
    Context context;
    kidsDatabaseHelper databaseHelper;
    kidsHomeAdapter homeAdapter;
    int position = 0;
    RecyclerView rvCategory;
    TextView okay_text, cancel_text;
    Dialog dialog;
    RelativeLayout ads_layout;
    kidsAppControl appControl;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        getSupportActionBar().hide();

        ads_layout = findViewById(R.id.ads_layout);
        appControl = (kidsAppControl) getApplication();

        this.context = this;
        initDefine();
        kidsDatabaseHelper databaseHelper2 = new kidsDatabaseHelper(this.context);
        this.databaseHelper = databaseHelper2;
        try {
            databaseHelper2.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialog = new Dialog(kidsMainAct.this);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        okay_text = dialog.findViewById(R.id.okay_text);
        cancel_text = dialog.findViewById(R.id.cancel_text);

        okay_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                dialog.dismiss();
            }
        });

        cancel_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    private void initDefine() {
        this.rvCategory = (RecyclerView) findViewById(R.id.rvCategory);
        this.arrOfCategory = new int[]{R.drawable.card_one, R.drawable.card_three, R.drawable.card_four};
        setRvAdapter();
    }

    public void onClickSetting(View view) {
        startActivityForResult(new Intent(this, kidsSettingAct.class), 111);
        appControl.loadInterAds(this);
    }

    private void setRvAdapter() {
        this.rvCategory.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        kidsHomeAdapter homeAdapter2 = new kidsHomeAdapter(this.context, this.arrOfCategory, new kidsHomeAdapter.onClickMain() {
            public void onClickCategory(int pos) {
                kidsMainAct.this.position = pos;
                if (Utils.getPref((Context) kidsMainAct.this, kidsConstant.CLICK_IMAGE_COUNT, (Integer) 1).intValue() == 1) {
                    Utils.setPref((Context) kidsMainAct.this, kidsConstant.CLICK_IMAGE_COUNT, (Integer) 2);
                    kidsMainAct.this.startNextActivity();
                    return;
                }
                kidsMainAct.this.checkAd();
            }
        });
        this.homeAdapter = homeAdapter2;
        this.rvCategory.setAdapter(homeAdapter2);
    }

    public void checkAd() {
        startNextActivity();
    }

    public void startNextActivity() {
        switch (this.position) {
            case 0:
                Intent intent1 = new Intent(this.context, kidsHomeAct.class);
                intent1.putExtra("Type", 1);
                this.context.startActivity(intent1);
                appControl.loadInterAds(this);

                return;
            case 1:
                Intent intent3 = new Intent(this.context, kidsHomeAct.class);
                intent3.putExtra("Type", 2);
                this.context.startActivity(intent3);
                appControl.loadInterAds(this);

                return;
            case 2:
                Intent intent4 = new Intent(this.context, kidsHomeAct.class);
                intent4.putExtra("Type", 3);
                this.context.startActivity(intent4);
                appControl.loadInterAds(this);

                return;
            default:
                return;
        }
    }

    @Override
    public void onBackPressed() {
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        appControl.loadAd(ads_layout);
    }
}
