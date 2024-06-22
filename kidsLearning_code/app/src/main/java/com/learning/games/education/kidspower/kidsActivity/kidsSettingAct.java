package com.learning.games.education.kidspower.kidsActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;
import com.learning.games.education.kidspower.kidsUtils.Utils;

public class kidsSettingAct extends AppCompatActivity {
    SwitchCompat soundOnOff;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_setting);
        getSupportActionBar().hide();
        initDefine();


    }

    private void initDefine() {
        this.soundOnOff = (SwitchCompat) findViewById(R.id.soundOnOff);
        if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
            this.soundOnOff.setChecked(true);
            kidsConstant.switchState = true;
        } else {
            kidsConstant.switchState = false;
            this.soundOnOff.setChecked(false);
        }
        this.soundOnOff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (kidsConstant.switchState) {
                    kidsConstant.switchState = false;
                    kidsSettingAct.this.soundOnOff.setChecked(false);
                    Utils.setPref(kidsConstant.SOUND, false);
                    return;
                }
                kidsConstant.switchState = true;
                kidsSettingAct.this.soundOnOff.setChecked(true);
                Utils.setPref(kidsConstant.SOUND, true);
            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }

    public void shareApp(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction("android.intent.action.SEND");
        shareIntent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + getPackageName());
        shareIntent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.app_name)));
    }


    public void RateUs(View view) {
        String appPackageName = getPackageName();
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public void MoreApp(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=Ninety+Nine+Apps")));
    }

    public void PrivacyPolicy(View view) {
        startActivity(new Intent(this, kidsPrivacyPolicyAct.class));
    }
}
