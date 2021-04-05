package com.example.car_eloung;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ad extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        ImageView ad1 = (ImageView) findViewById(R.id.ad1);
        ImageView ad2 = (ImageView) findViewById(R.id.ad2);
        ImageView ad3 = (ImageView) findViewById(R.id.ad3);
        ImageView ad4 = (ImageView) findViewById(R.id.ad4);
        ImageView ad5 = (ImageView) findViewById(R.id.ad5);


        //Last Layout
        TextView textView = (TextView)findViewById(R.id.text);
        ImageView ImageView = (ImageView) findViewById(R.id.button);
        ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(ad.this, Car_E_Lounge.class);
                startActivity(s);
                finish();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ad1.setVisibility(View.INVISIBLE);
                ad2.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ad2.setVisibility(View.INVISIBLE);
                        ad3.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ad3.setVisibility(View.INVISIBLE);
                                ad4.setVisibility(View.VISIBLE);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ad4.setVisibility(View.INVISIBLE);
                                        ad5.setVisibility(View.VISIBLE);
                                        ImageView.setVisibility(View.VISIBLE);
                                        textView.setVisibility(View.VISIBLE);
                                    }
                                }, SPLASH_TIME_OUT);
                            }
                        }, SPLASH_TIME_OUT);
                    }
                }, SPLASH_TIME_OUT);
            }
        }, SPLASH_TIME_OUT);
    }
}