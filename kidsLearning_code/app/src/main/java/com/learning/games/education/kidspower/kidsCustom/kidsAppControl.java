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

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsUtils.Utils;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class kidsAppControl extends Application {
    public static TextToSpeech textToSpeech;
    Context context;

    FirebaseFirestore firestore;
    AdRequest adRequest;
    InterstitialAd mInterstitialAd;
    String googleBanner, googleInter;
    Boolean status;
    AdView mAdView;

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

        //Google Banner ads code
        firestore = FirebaseFirestore.getInstance();
        mAdView = new AdView(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adRequest = new AdRequest.Builder().build();

        firestore.collection("KidsLearningAds")
                .document("Ads")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            googleBanner = snapshot.getString("GoogleBannerAds");
                            googleInter = snapshot.getString("GoogleInterAds");
                            status = snapshot.getBoolean("Status");

                            Log.d("===", "onComplete: " + status);
                            if (status.equals(true)) {
                                mAdView.setAdSize(AdSize.BANNER);
                                mAdView.setAdUnitId(googleBanner);
                                mAdView.loadAd(adRequest);

                                InterAds();

                            }

                        }
                    }
                });
    }

    public void loadAd(RelativeLayout layAd) {

        if (mAdView.getParent() != null) {
            ViewGroup tempVg = (ViewGroup) mAdView.getParent();
            tempVg.removeView(mAdView);
        }
        layAd.addView(mAdView);
    }

    public void InterAds() {
        InterstitialAd.load(this, googleInter, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

    public void loadInterAds(Activity context) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(context);

            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    InterAds();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    mInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                }

                @Override
                public void onAdShowedFullScreenContent() {
                }
            });
        }
    }


}
