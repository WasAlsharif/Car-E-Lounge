package com.example.car_eloung;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
public class logo_activity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);
        
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent ad = new Intent(logo_activity.this, ad.class);
                startActivity(ad);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}
