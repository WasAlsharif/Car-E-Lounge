package com.example.car_eloung;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Rapid_Miner extends AppCompatActivity {
    DatabaseReference myRef;
    TextView loading;
    ImageView Tran, home, start_compare;
    ProgressBar progressBar;
    String companys[] = new String[96];
    int i = 0, j = 0;
    ArrayList<String> models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_activity);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Tran = (ImageView) findViewById(R.id.imageView6);
        loading = (TextView) findViewById(R.id.Loading);
        start_compare = (ImageView) findViewById(R.id.start_compare);
        home = (ImageView) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Rapid_Miner.this, Car_E_Lounge.class));
                finish();
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("companys");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String temp = String.valueOf(i);
                    companys[i] = dataSnapshot.child(temp).child("Car Company").getValue().toString();
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.company_1);
        Spinner spinner2 = (Spinner) findViewById(R.id.company_2);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, companys);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                models = new ArrayList<>();
                progressBar.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
                Tran.setVisibility(View.INVISIBLE);
                spinner.setAdapter(spinnerArrayAdapter);
                spinner2.setAdapter(spinnerArrayAdapter);
             /*   spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        myRef = database.getReference();
                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    String temp = String.valueOf(j);
                                    if(dataSnapshot.child(temp).child("Car Company").getValue().toString()==spinner.getSelectedItem()){
                                         models.add(dataSnapshot.child(temp).child("Model").getValue().toString());
                                    }
                                     j++;
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });*/

            }
        }, 3000);   //3 seconds

/*
        Spinner model1 = (Spinner) findViewById(R.id.model_1);
        Spinner model2 = (Spinner) findViewById(R.id.model_2);
        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, models);
        spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                model1.setAdapter(spinnerArrayAdapter3);
            }
        }, 3000);   //3 seconds
*/
        start_compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
