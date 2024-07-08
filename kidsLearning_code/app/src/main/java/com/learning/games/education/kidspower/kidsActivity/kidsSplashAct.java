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

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsUtils.Utils;

public class kidsSplashAct extends AppCompatActivity {
    Context context;
    FirebaseFirestore firestore;
    String app_Id;
    Boolean status;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash);
        getSupportActionBar().hide();
        this.context = this;

        boolean isConnected = NetworkUtils.isInternetAvailable(getApplicationContext());
        firestore = FirebaseFirestore.getInstance();

        firestore.collection("KidsLearningAds")
                .document("Ads")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            app_Id = snapshot.getString("App_Id");
                            status = snapshot.getBoolean("Status");

                            Log.d("===", "onComplete: " + status);
                            if (status.equals(true)) {
                                try {
                                    ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                                    Bundle bundle = applicationInfo.metaData;
                                    applicationInfo.metaData.putString("com.google.android.gms.ads.APPLICATION_ID", app_Id);
                                    String apikey = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
                                    Log.d("===", "" + apikey);
                                } catch (PackageManager.NameNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        }
                    }
                });

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
