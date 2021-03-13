package com.example.car_eloung;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Rapid_Miner extends AppCompatActivity {
    DatabaseReference myRef;
    TextView loading;
    ImageView Tran, home, start_compare;
    ProgressBar progressBar;
    String companys[] = new String[96];
    String Value = null, Value2 = null;
    int i = 0, j = 0, k = 0, t = 0;
    ArrayList<String> models;
    ArrayList<String> models2;
    ArrayList<String> Default;
    FirebaseDatabase database;
    Spinner model1;
    Spinner model2;
    String Company, Model, Company2, Model2;
    TextView textView;
    TextView Fcar;
    TextView Scar;
    String model1_value;
    String model2_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_activity);
        model1 = (Spinner) findViewById(R.id.model_1);
        model2 = (Spinner) findViewById(R.id.model_2);

        models = new ArrayList<>();
        models2 = new ArrayList<>();
        Default = new ArrayList<>();
        Default.add("Choose Car Company First");
        ArrayAdapter<String> spinnerArrayAdapter0 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, Default);
        spinnerArrayAdapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        model1.setAdapter(spinnerArrayAdapter0);
        // model2.setAdapter(spinnerArrayAdapter0);

        textView = (TextView) findViewById(R.id.textView16);
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

        database = FirebaseDatabase.getInstance();
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
                progressBar.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
                Tran.setVisibility(View.INVISIBLE);
                spinner.setAdapter(spinnerArrayAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        models.removeAll(models);
                        Value = spinner.getSelectedItem().toString();
                        Grab_models(Value);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });
            }
        }, 3000);   //3 seconds

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, companys);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                spinner2.setAdapter(spinnerArrayAdapter2);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        models2.removeAll(models2);
                        Value2 = spinner2.getSelectedItem().toString();
                        Grab_models2(Value2);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });
            }
        }, 3000);   //3 seconds


        start_compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("Select Car Company:") && !spinner2.getSelectedItem().toString().equals("Select Car Company:")) {
                    setContentView(R.layout.compare_activity2);
                    Fcar = (TextView) findViewById(R.id.Fcar);
                    Scar = (TextView) findViewById(R.id.Scar);
                    Result();
                } else {
                    Toast.makeText(Rapid_Miner.this, "Fill all the requirements first!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void Grab_models(String value) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String temp = String.valueOf(j);
                    j++;
                    // Company = dataSnapshot.child("models").child(temp).child("Car Company").getValue().toString();
                    try {
                        Company = dataSnapshot.child("models").child(temp).child("Car Company").getValue().toString();
                        Model = dataSnapshot.child("models").child(temp).child("Model").getValue().toString();
                        if (Company.equalsIgnoreCase(value)) {
                            models.add(Model);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                Display_model();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        j = 0;
    }

    public void Grab_models2(String value) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String temp = String.valueOf(k);
                    k++;
                    try {
                        Company2 = dataSnapshot.child("models").child(temp).child("Car Company").getValue().toString();
                        Model2 = dataSnapshot.child("models").child(temp).child("Model").getValue().toString();
                        if (Company2.equalsIgnoreCase(value)) {
                            models2.add(Model2);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                Display_model2();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        k = 0;
    }


    public void Display_model() {
        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, models);
        spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        model1.setAdapter(spinnerArrayAdapter3);
        model1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model1_value = model1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void Display_model2() {
        ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, models2);
        spinnerArrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        model2.setAdapter(spinnerArrayAdapter4);
        model2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model2_value = model2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void Result() {
        Fcar.setText(model1_value);
        Scar.setText(model2_value);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String temp2 = String.valueOf(t);
                    t++;
                    if (dataSnapshot.child(temp2).child("Model").equals(model1_value)) {
                        String Car_Image = dataSnapshot.child(temp2).child("URL Image").getValue().toString();
                        String To100sec = dataSnapshot.child(temp2).child("0-100 kph (sec)").getValue().toString();
                        String Body_Style = dataSnapshot.child(temp2).child("Body Styles").getValue().toString();
                        String Company_Logo = dataSnapshot.child(temp2).child("Brand Logo").getValue().toString();
                        String Company_Name = dataSnapshot.child(temp2).child("Car Company").getValue().toString();
                        String Class = dataSnapshot.child(temp2).child("Class").getValue().toString();
                        String Country = dataSnapshot.child(temp2).child("Country of Origin").getValue().toString();
                        String Engine = dataSnapshot.child(temp2).child("Engine").getValue().toString();
                        String Gearbox = dataSnapshot.child(temp2).child("Gearbox").getValue().toString();
                        String Power = dataSnapshot.child(temp2).child("Power (hp)").getValue().toString();
                        String Speed = dataSnapshot.child(temp2).child("Top speed (kph)").getValue().toString();
                        String Torque = dataSnapshot.child(temp2).child("Torque (Nm)").getValue().toString();
                        String Price = dataSnapshot.child(temp2).child("Price").getValue().toString();
                        String Year = dataSnapshot.child(temp2).child("Year").getValue().toString();
                        String Bad = dataSnapshot.child(temp2).child("The bad").getValue().toString();
                        String Good = dataSnapshot.child(temp2).child("The good").getValue().toString();
                        Toast.makeText(Rapid_Miner.this, "", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
