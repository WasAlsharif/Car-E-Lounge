package com.example.car_eloung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import weka.core.Capabilities;
import weka.core.CapabilitiesHandler;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.RevisionHandler;
import weka.core.RevisionUtils;
import weka.core.SerializedObject;
import weka.core.Utils;
import weka.core.Capabilities.Capability;

public class Questionnaire extends AppCompatActivity {
    int i = 0;
    Button A, B, C, D;
    TextView question;
    Button circle1, circle2, circle3, circle4, circle5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);
        A = (Button) findViewById(R.id.buttonA);
        B = (Button) findViewById(R.id.buttonB);
        C = (Button) findViewById(R.id.buttonC);
        D = (Button) findViewById(R.id.buttonD);
        question = (TextView) findViewById(R.id.question);

        String Question1is = "";
        String Question2is = "";
        String Question3is = "";
        String Question4is = "";
        String Question5is = "";
        String[] Question1 = new String[10];


        questions(i);
        final ImageView back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(Questionnaire.this)
                        .setTitle("Exit")
                        .setMessage("Are you sure do you want to back?")
                        .setPositiveButton("Yes", null)
                        .setNegativeButton("Cancel", null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(new Intent(Questionnaire.this, Car_E_Lounge.class));
                        finish(); // Here We Have Problem when close the activity
                    }
                });

            }
        });

        //We will hiding those texts in first step
        final TextView text1 = (TextView) findViewById(R.id.HeaderText);
        final TextView text2 = (TextView) findViewById(R.id.textView7);
        final TextView text3 = (TextView) findViewById(R.id.textView8);
        final Button start = (Button) findViewById(R.id.Start);


        //Second Step show those


        circle1 = (Button) findViewById(R.id.circle1);
        circle2 = (Button) findViewById(R.id.circle2);
        circle3 = (Button) findViewById(R.id.circle3);
        circle4 = (Button) findViewById(R.id.circle4);
        circle5 = (Button) findViewById(R.id.circle5);

        ImageView information = (ImageView) findViewById(R.id.information);

        //When click on the start button will hiding the last texts

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                start.setVisibility(View.INVISIBLE);
                information.setVisibility(View.VISIBLE);
                question.setVisibility(View.VISIBLE);
                A.setVisibility(View.VISIBLE);
                B.setVisibility(View.VISIBLE);
                C.setVisibility(View.VISIBLE);
                D.setVisibility(View.VISIBLE);
                circle1.setVisibility(View.VISIBLE);
                circle2.setVisibility(View.VISIBLE);
                circle3.setVisibility(View.VISIBLE);
                circle4.setVisibility(View.VISIBLE);
                circle5.setVisibility(View.VISIBLE);
                //====================================================================
                //Second Step

                circle1.setBackgroundResource(R.drawable.progress_circle2);

                if (i <= 7) {
                    A.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Question1[i] = A.getText().toString();
                            i++;
                            questions(i);
                        }

                    });
                    B.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Question1[i] = B.getText().toString();
                            i++;
                            questions(i);
                        }

                    });
                    C.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Question1[i] = C.getText().toString();
                            i++;
                            questions(i);
                        }

                    });
                    D.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Question1[i] = D.getText().toString();
                            i++;
                            questions(i);
                        }

                    });
                }

                information.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Questionnaire.this, "Answers: " + Question1[0] + " " + Question1[1] + " " + Question1[2] + " " + Question1[3] + " " + Question1[4], Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    void questions(int i) {
        //Question 1 is already added it
        //Question 2
        if (i == 0) {
            question.setText("Question 1");
            A.setText("A");
            B.setText("B");
            C.setText("C");
            D.setText("D");
        } else if (i == 1) {
            question.setText("Question 2");
            A.setText("E");
            B.setText("F");
            C.setText("G");
            D.setText("H");
            circle1.setBackgroundResource(R.drawable.progress_circle);
            circle2.setBackgroundResource(R.drawable.progress_circle2);
        }
        //Question 3
        else if (i == 2) {
            question.setText("Question 3");
            A.setText("I");
            B.setText("J");
            C.setText("K");
            D.setText("L");
            circle2.setBackgroundResource(R.drawable.progress_circle);
            circle3.setBackgroundResource(R.drawable.progress_circle2);
        }
        //Question 4
        else if (i == 3) {
            question.setText("Question 4");
            A.setText("M");
            B.setText("N");
            C.setText("O");
            D.setText("P");
            circle3.setBackgroundResource(R.drawable.progress_circle);
            circle4.setBackgroundResource(R.drawable.progress_circle2);
        }
        //Question 5
        else if (i == 4) {
            question.setText("Question 5");
            A.setText("Q");
            B.setText("R");
            C.setText("S");
            D.setText("T");
            circle4.setBackgroundResource(R.drawable.progress_circle);
            circle5.setBackgroundResource(R.drawable.progress_circle2);
        } else {

        }
    }


}
