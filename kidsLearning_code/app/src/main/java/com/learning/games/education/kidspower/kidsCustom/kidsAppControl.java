package com.learning.games.education.kidspower.kidsCustom;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsUtils.Utils;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class kidsAppControl extends Application {
    public static TextToSpeech textToSpeech;
    Context context;

    String googleBanner, googleInter;
    Boolean status;


    public void onCreate() {
        super.onCreate();

        this.context = this;
        new Utils(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("Fon/OhWhale.ttf").setFontAttrId(R.attr.fontPath).build());
        TextToSpeech textToSpeech2 = new TextToSpeech(this.context, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status != -1) {
                    kidsAppControl.textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        textToSpeech = textToSpeech2;
        textToSpeech2.setSpeechRate(0.7f);


    }

    public void loadAd(RelativeLayout layAd) {

    }

    public void InterAds() {

    }

    public void loadInterAds(Activity context) {

    }


}
