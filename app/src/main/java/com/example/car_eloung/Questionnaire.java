package com.example.car_eloung;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Questionnaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);

        final ImageView back = (ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Questionnaire.this,Car_E_Lounge.class));
                finish(); // Here We Have Problem when close the activity
            }
        });

        //We will hiding those texts in first step
        final TextView text1 = (TextView)findViewById(R.id.HeaderText);
        final TextView text2 = (TextView)findViewById(R.id.textView7);
        final TextView text3 = (TextView)findViewById(R.id.textView8);
        final Button start = (Button)findViewById(R.id.Start);


        //Second Step show those
        final TextView question = (TextView)findViewById(R.id.question);

        final Button circle1 = (Button)findViewById(R.id.circle1);
        final Button circle2 = (Button)findViewById(R.id.circle2);
        final Button circle3 = (Button)findViewById(R.id.circle3);
        final Button circle4 = (Button)findViewById(R.id.circle4);
        final Button circle5 = (Button)findViewById(R.id.circle5);
        final Button circle6 = (Button)findViewById(R.id.circle6);



        //When click on the start button will hiding the last texts

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                start.setVisibility(View.INVISIBLE);

                //Second Step
                question.setVisibility(View.VISIBLE);
                circle1.setVisibility(View.VISIBLE);
                circle1.setBackgroundColor(R.drawable.progress_circle2);
                circle2.setVisibility(View.VISIBLE);
                circle3.setVisibility(View.VISIBLE);
                circle4.setVisibility(View.VISIBLE);
                circle5.setVisibility(View.VISIBLE);
                circle6.setVisibility(View.VISIBLE);

            }
        });


        String Question1is = "";
        String Question2is = "";
        String Question3is = "";
        String Question4is = "";
        String Question5is = "";


        String[] Question1 = new String[3];
        String[] Question2 = new String[3];
        String[] Question3 = new String[3];
        String[] Question4 = new String[3];
        String[] Question5 = new String[3];



    }

}
