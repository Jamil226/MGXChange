package com.app.trendize.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.app.trendize.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        }
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // User Pref
                    Intent intent;
                    SharedPreferences prefPersonalUser = getSharedPreferences("userData", MODE_PRIVATE);
                    String emailPersonalUser = prefPersonalUser.getString("email", "null");
                    if (!emailPersonalUser.equals("null")) {
                        intent = new Intent(SplashScreen.this, Dashboard.class);
                    } else {
                        intent = new Intent(SplashScreen.this, Welcome.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }
        };
        t.start();
    }
}