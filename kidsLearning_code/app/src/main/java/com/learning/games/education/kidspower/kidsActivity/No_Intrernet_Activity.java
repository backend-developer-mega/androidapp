package com.learning.games.education.kidspower.kidsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.learning.games.education.kidspower.R;

public class No_Intrernet_Activity extends AppCompatActivity {

    Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_intrernet);

        btnRetry = findViewById(R.id.btnRetry);
        getSupportActionBar().hide();

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check initial internet connection when the activity is created
                if (isInternetAvailable()) {
                    startActivity(new Intent(No_Intrernet_Activity.this,kidsMainAct.class));
                    finish();
                } else {
                    // Internet connection is not available, show the retry button
                }
            }
        });
    }
    private boolean isInternetAvailable() {
        // Implement your internet connectivity checking logic here (you can use the NetworkUtils method from the previous answer)
        return NetworkUtils.isInternetAvailable(getApplicationContext());
    }
}