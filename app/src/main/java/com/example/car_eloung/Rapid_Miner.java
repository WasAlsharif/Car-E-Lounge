package com.example.car_eloung;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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

import java.net.URL;
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
    TextView car;
    String model1_value;
    String model2_value;

    String Company_Logo1, Company_Name1, Car_Image1, To100sec1, Body_Style1, Class1, Country1, Engine1, Gearbox1, Power1, Speed1, Torque1, Price1, Year1, Good1, Bad1;
    String Company_Logo2, Company_Name2, Car_Image2, To100sec2, Body_Style2, Class2, Country2, Engine2, Gearbox2, Power2, Speed2, Torque2, Price2, Year2, Good2, Bad2;

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
                    car = (TextView) findViewById(R.id.car);
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
        ImageView ImgCar1 = (ImageView) findViewById(R.id.Img_car1);
        ImageView ImgCar2 = (ImageView) findViewById(R.id.Img_car2);
        ImageView Company_logo1 = (ImageView) findViewById(R.id.company_logo1);
        ImageView Company_logo2 = (ImageView) findViewById(R.id.company_logo2);
        car.setText(model1_value + " VS " + model2_value);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String temp2 = String.valueOf(t);
                    t++;
                    try {
                        String Check = dataSnapshot.child(temp2).child("Model").getValue().toString();
                        if (Check.equalsIgnoreCase(model1_value)) {
                            TextView TCompany_Name1 = (TextView) findViewById(R.id.Company1_name);
                            TextView TTo100sec1 = (TextView) findViewById(R.id.To100sec1);
                            TextView TBody_Style1 = (TextView) findViewById(R.id.Body_Style1);
                            TextView TClass1 = (TextView) findViewById(R.id.Class1);
                            TextView TCountry1 = (TextView) findViewById(R.id.Country1);
                            TextView TEngine1 = (TextView) findViewById(R.id.Engine1);
                            TextView TGearbox1 = (TextView) findViewById(R.id.Gearbox1);
                            TextView TPower1 = (TextView) findViewById(R.id.Power1);
                            TextView TSpeed1 = (TextView) findViewById(R.id.Speed1);
                            TextView TTorque1 = (TextView) findViewById(R.id.Torque1);
                            TextView TPrice1 = (TextView) findViewById(R.id.Price1);
                            TextView TYear1 = (TextView) findViewById(R.id.Year1);
                            TextView TGood1 = (TextView) findViewById(R.id.Good1);
                            TextView TBad1 = (TextView) findViewById(R.id.Bad1);


                            Company_Logo1 = dataSnapshot.child(temp2).child("Brand Logo").getValue().toString();
                            Company_Name1 = dataSnapshot.child(temp2).child("Car Company").getValue().toString();
                            Car_Image1 = dataSnapshot.child(temp2).child("URL Image").getValue().toString();
                            To100sec1 = dataSnapshot.child(temp2).child("0-100 kph (sec)").getValue().toString();
                            Body_Style1 = dataSnapshot.child(temp2).child("Body Styles").getValue().toString();
                            Class1 = dataSnapshot.child(temp2).child("Class").getValue().toString();
                            Country1 = dataSnapshot.child(temp2).child("Country of Origin").getValue().toString();
                            Engine1 = dataSnapshot.child(temp2).child("Engine").getValue().toString();
                            Gearbox1 = dataSnapshot.child(temp2).child("Gearbox").getValue().toString();
                            Power1 = dataSnapshot.child(temp2).child("Power (hp)").getValue().toString();
                            Speed1 = dataSnapshot.child(temp2).child("Top speed (kph)").getValue().toString();
                            Torque1 = dataSnapshot.child(temp2).child("Torque (Nm)").getValue().toString();
                            Price1 = dataSnapshot.child(temp2).child("Price").getValue().toString();
                            Year1 = dataSnapshot.child(temp2).child("Year").getValue().toString();
                            Good1 = dataSnapshot.child(temp2).child("The good").getValue().toString();
                            Bad1 = dataSnapshot.child(temp2).child("The bad").getValue().toString();


                            Picasso.with(Rapid_Miner.this).load(Company_Logo1)
                                    .into(Company_logo1);

                            Picasso.with(Rapid_Miner.this).load(Car_Image1)
                                    .into(ImgCar1);
                            TCompany_Name1.setText(Company_Name1);
                            TTo100sec1.setText(To100sec1);
                            TBody_Style1.setText(Body_Style1);
                            TClass1.setText(Class1);
                            TCountry1.setText(Country1);
                            TEngine1.setText(Engine1);
                            TGearbox1.setText(Gearbox1);
                            TPower1.setText(Power1);
                            TSpeed1.setText(Speed1);
                            TTorque1.setText(Torque1);
                            TPrice1.setText(Price1);
                            TYear1.setText(Year1);
                            TGood1.setText(Good1);
                            TBad1.setText(Bad1);
                        }
                        if (Check.equalsIgnoreCase(model2_value)) {
                            TextView TCompany_Name2 = (TextView) findViewById(R.id.Company2_name);
                            TextView TTo100sec2 = (TextView) findViewById(R.id.To100sec2);
                            TextView TBody_Style2 = (TextView) findViewById(R.id.Body_Style2);
                            TextView TClass2 = (TextView) findViewById(R.id.Class2);
                            TextView TCountry2 = (TextView) findViewById(R.id.Country2);
                            TextView TEngine2 = (TextView) findViewById(R.id.Engine2);
                            TextView TGearbox2 = (TextView) findViewById(R.id.Gearbox2);
                            TextView TPower2 = (TextView) findViewById(R.id.Power2);
                            TextView TSpeed2 = (TextView) findViewById(R.id.Speed2);
                            TextView TTorque2 = (TextView) findViewById(R.id.Torque2);
                            TextView TPrice2 = (TextView) findViewById(R.id.Price2);
                            TextView TYear2 = (TextView) findViewById(R.id.Year2);
                            TextView TGood2 = (TextView) findViewById(R.id.Good2);
                            TextView TBad2 = (TextView) findViewById(R.id.Bad2);


                            Company_Logo2 = dataSnapshot.child(temp2).child("Brand Logo").getValue().toString();
                            Company_Name2 = dataSnapshot.child(temp2).child("Car Company").getValue().toString();
                            Car_Image2 = dataSnapshot.child(temp2).child("URL Image").getValue().toString();
                            To100sec2 = dataSnapshot.child(temp2).child("0-100 kph (sec)").getValue().toString();
                            Body_Style2 = dataSnapshot.child(temp2).child("Body Styles").getValue().toString();
                            Class2 = dataSnapshot.child(temp2).child("Class").getValue().toString();
                            Country2 = dataSnapshot.child(temp2).child("Country of Origin").getValue().toString();
                            Engine2 = dataSnapshot.child(temp2).child("Engine").getValue().toString();
                            Gearbox2 = dataSnapshot.child(temp2).child("Gearbox").getValue().toString();
                            Power2 = dataSnapshot.child(temp2).child("Power (hp)").getValue().toString();
                            Speed2 = dataSnapshot.child(temp2).child("Top speed (kph)").getValue().toString();
                            Torque2 = dataSnapshot.child(temp2).child("Torque (Nm)").getValue().toString();
                            Price2 = dataSnapshot.child(temp2).child("Price").getValue().toString();
                            Year2 = dataSnapshot.child(temp2).child("Year").getValue().toString();
                            Good2 = dataSnapshot.child(temp2).child("The good").getValue().toString();
                            Bad2 = dataSnapshot.child(temp2).child("The bad").getValue().toString();
                            Picasso.with(Rapid_Miner.this).load(Company_Logo2)
                                    .into(Company_logo2);

                            Picasso.with(Rapid_Miner.this).load(Car_Image2)
                                    .into(ImgCar2);

                            TCompany_Name2.setText(Company_Name2);
                            TTo100sec2.setText(To100sec2);
                            TBody_Style2.setText(Body_Style2);
                            TClass2.setText(Class2);
                            TCountry2.setText(Country2);
                            TEngine2.setText(Engine2);
                            TGearbox2.setText(Gearbox2);
                            TPower2.setText(Power2);
                            TSpeed2.setText(Speed2);
                            TTorque2.setText(Torque2);
                            TPrice2.setText(Price2);
                            TYear2.setText(Year2);
                            TGood2.setText(Good2);
                            TBad2.setText(Bad2);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
