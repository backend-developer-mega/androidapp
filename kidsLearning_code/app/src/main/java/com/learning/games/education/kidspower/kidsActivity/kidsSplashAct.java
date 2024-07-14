package com.learning.games.education.kidspower.kidsActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsUtils.Utils;

public class kidsSplashAct extends AppCompatActivity {
    Context context;
    //FirebaseFirestore firestore;
    String app_Id;
    Boolean status;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash);
        getSupportActionBar().hide();
        this.context = this;

        boolean isConnected = NetworkUtils.isInternetAvailable(getApplicationContext());
        if (isConnected)
        {
            startNextActivity(6000);
        }
        else {
            startActivity(new Intent(kidsSplashAct.this,No_Intrernet_Activity.class));
            finish();
        }
    }

    public void startNextActivity(Integer time) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                kidsSplashAct.this.startActivity(new Intent(kidsSplashAct.this, kidsUnitMainAct.class));
                kidsSplashAct.this.finish();
            }
        }, (long) time.intValue());
    }


}
