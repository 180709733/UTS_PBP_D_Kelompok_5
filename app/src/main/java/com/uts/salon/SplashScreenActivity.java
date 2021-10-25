package com.uts.salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.uts.salon.Preferences.UserPreferences;
import com.uts.salon.ui.auth.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        userPreferences = new UserPreferences(SplashScreenActivity.this);


        checkLogin();


    }


    private void checkLogin() {
        if (userPreferences.checkLogin()) {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        } else {
            // ngebuat layar jadi penuh dan waktu tampil//
            View decorView = getWindow().getDecorView();

            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }
            }, 3000);
        }
    }
}