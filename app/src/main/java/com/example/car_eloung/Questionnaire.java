package com.example.car_eloung;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.DenseInstance;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Questionnaire extends AppCompatActivity {
    int i = 0;
    ArrayList<Car> cars = new ArrayList<Car>();// this array stores all the cars
    ArrayList<Car> fcars = new ArrayList<Car>();// this array stores only filtered cars based on user answers
    //declare variables
    TextView question, loading, brand, model, more;
    Button circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8, circle9, circle10;
    ImageView back, Tran, car_pic;
    ProgressBar progressBar;
    ListView listView;// listview
    String[] answers;// store user answers
    // adapters to show options
    ArrayAdapter<String> priceadapter, topspeedadapter, bodyadapter, styleadapter,
            poweradapter, countryadapter;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_activity);

        listView = (ListView) findViewById(R.id.listView);
        //textView=(TextView)findViewById(R.id.textView);
        question = (TextView) findViewById(R.id.question);
        // -----------------------------------------------------------------------------------------
        cars = loadCars();
        // -----------------------------------------------------------------------------------------
        // set default options to each adapter
        // the default options are defined inside DataConstants.java file.
        priceadapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, DataConstants.price);
        countryadapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, DataConstants.country);
        bodyadapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, DataConstants.body);
        styleadapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, DataConstants.style);

        answers = new String[10];// create array instance to store user answers
        back = (ImageView) findViewById(R.id.back);

        // if user click on back button
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
                        finish();
                    }
                });

            }
        });

        //We will hiding those texts in first step
        final TextView text1 = (TextView) findViewById(R.id.HeaderText);
        final TextView text2 = (TextView) findViewById(R.id.textView7);
        final TextView text3 = (TextView) findViewById(R.id.textView8);


        //Second Step show those
        circle1 = (Button) findViewById(R.id.circle1);
        circle2 = (Button) findViewById(R.id.circle2);
        circle3 = (Button) findViewById(R.id.circle3);
        circle4 = (Button) findViewById(R.id.circle4);
        circle5 = (Button) findViewById(R.id.circle5);
        circle6 = (Button) findViewById(R.id.circle6);
        circle7 = (Button) findViewById(R.id.circle7);
        circle8 = (Button) findViewById(R.id.circle8);
        circle9 = (Button) findViewById(R.id.circle9);
        circle10 = (Button) findViewById(R.id.circle10);

        //When click on the start button will hiding the last texts
        Button start = (Button) findViewById(R.id.startquestions);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                start.setVisibility(View.INVISIBLE);
                question.setVisibility(View.VISIBLE);
                // show first question to user, price range
                question.setText("Price Range");
                listView.setAdapter(priceadapter);// set price adapter to listview to show price range to user
                circle1.setVisibility(View.VISIBLE);
                circle2.setVisibility(View.VISIBLE);
                circle3.setVisibility(View.VISIBLE);
                circle4.setVisibility(View.VISIBLE);
                circle5.setVisibility(View.VISIBLE);
                circle6.setVisibility(View.VISIBLE);
                //====================================================================
                //Second Step

                circle1.setBackgroundResource(R.drawable.progress_circle2);
                if (i <= 9) {
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            // TODO Auto-generated method stub
                            // there are total 6 questions
                            String value = null;
                            if (pos == 0)// if user on question 1
                                value = priceadapter.getItem(position);// then it will be price
                            if (pos == 1)// if user on question 2
                                value = countryadapter.getItem(position);// it will be a country
                            if (pos == 2)// if user on question 3
                                value = bodyadapter.getItem(position);// it will be a body
                            if (pos == 3)// and so on
                                value = styleadapter.getItem(position);
                            if (pos == 4)
                                value = poweradapter.getItem(position);
                            if (pos == 5)
                                value = topspeedadapter.getItem(position);

                            answers[i] = value;// store the user answer
                            i++;// go to next question
                            questions(i);// load next question

                        }
                    });
                }

            }
        });
    }
    // here it will load next question
    void questions(int i) {
//--------------------------------------------------------------------------------------------------
        // the question1 already shown to user
        if (i == 0) {
            question.setText("Price Range");
            listView.setAdapter(priceadapter);
            pos = 0;
        } else if (i == 1) // question 2
        {
            question.setText("Select Country");// set text
            ArrayList<String> values = new ArrayList<String>();// create temperary array
            // now from the previous question, we have price range
            // price format: 100-200, or 1000 and more
            // so we need to parse price string so we can get price as integer
            String price = answers[0];// get selected price
            int aggregatedprice = 0;
            String arr[] = null;


            arr = price.split("-");
            int pricestart = Integer.parseInt(arr[0]);
            int priceend = Integer.parseInt(arr[1]);
            // filter the cars based on price range
            for (int k = 0; k < cars.size(); k++) {
                if (cars.get(k).getPrice() >= pricestart && cars.get(k).getPrice() <= priceend) {
                    if (!values.contains(cars.get(k).getCountry()))
                        values.add(cars.get(k).getCountry());// get country
                    fcars.add(cars.get(k));
                }
            }
            //           }
            // now we have filtered cars based on price range
            // create/update country list adapter
            countryadapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, values);
            // here will only show the countries which statisfies price range selected in previous question
            listView.setAdapter(countryadapter);
            listView.invalidate();
            pos = 1;
            circle1.setBackgroundResource(R.drawable.progress_circle);
            circle2.setBackgroundResource(R.drawable.progress_circle2);
        }

        //Question 3
        else if (i == 2) {
            question.setText("Select Body");// question 3
            ArrayList<String> values = new ArrayList<>();// create array
            String country = answers[1];// get country
            // now from previous step the fcars array contains the cars which statisfies price range
            // now will filter cars based on country selected in previous cars
            for (int k = 0; k < fcars.size(); k++) {
                if (fcars.get(k).getCountry().equalsIgnoreCase(country)) {
                    if (!values.contains(fcars.get(k).getBody()))
                        values.add(fcars.get(k).getBody());

                } else {
                    fcars.remove(fcars.get(k));
                }

            }
            // now update body adapters
            // we have filtered cars based on price range and country so from the filtered cars
            // we show car body so user can select
            bodyadapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, values);
            listView.setAdapter(bodyadapter);
            listView.invalidate();
            pos = 2;
            circle2.setBackgroundResource(R.drawable.progress_circle);
            circle3.setBackgroundResource(R.drawable.progress_circle2);
        }
        //Question 4
        else if (i == 3) {
            // the same as previous step but now we will filter cars based on user selected body
            // we gradually filtered cars based on user answers like we are building a tree
            question.setText("Select Style");
            String body = answers[2];
            ArrayList<String> values = new ArrayList<>();
            for (int k = 0; k < fcars.size(); k++) {
                if (fcars.get(k).getBody().equalsIgnoreCase(body)) {
                    if (!values.contains(fcars.get(k).getStyle()))
                        values.add(fcars.get(k).getStyle());

                } else {
                    fcars.remove(fcars.get(k));
                }

            }
            styleadapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, values);

            listView.setAdapter(styleadapter);
            listView.invalidate();
            pos = 3;
            circle3.setBackgroundResource(R.drawable.progress_circle);
            circle4.setBackgroundResource(R.drawable.progress_circle2);
        }
        //Question 5
        else if (i == 4) {
            // now we will ask users the power range
            question.setText("Power");
            ArrayList<Integer> values = new ArrayList<>();
            // filter power range based on style and body
            for (int k = 0; k < fcars.size(); k++) {
                values.add(fcars.get(k).getPowerhp());
            }
            Collections.sort(values);
            int lowerbound = values.get(0);
            int upperbound = values.get(values.size() - 1);
            String[] range = Utils.range(lowerbound, upperbound);
            poweradapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, range);
            listView.setAdapter(poweradapter);
            listView.invalidate();
            pos = 4;
            circle4.setBackgroundResource(R.drawable.progress_circle);
            circle5.setBackgroundResource(R.drawable.progress_circle2);

        }

        //Question 6
        else if (i == 5) {
            // now we will ask from users top speed range
            question.setText("Top Speed");
            ArrayList<Integer> values = new ArrayList<>();
            // filter Top Speed based on style and body
            for (int k = 0; k < fcars.size(); k++) {
                values.add(fcars.get(k).getTopspeed());
            }
            Collections.sort(values);
            int lowerbound = values.get(0);
            int upperbound = values.get(values.size() - 1);
            String[] range = Utils.range(lowerbound, upperbound);

            topspeedadapter = new ArrayAdapter<String>(this, R.layout.q_options, R.id.options, range);
            listView.setAdapter(topspeedadapter);
            listView.invalidate();
            pos = 5;
            circle5.setBackgroundResource(R.drawable.progress_circle);
            circle6.setBackgroundResource(R.drawable.progress_circle2);

        } else {
            //Here we move to another layout
            setContentView(R.layout.questions_activity2);
            more = (TextView) findViewById(R.id.textView10);
            //These are from Layout 2
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            Tran = (ImageView) findViewById(R.id.imageView6);
            loading = (TextView) findViewById(R.id.Loading);
            car_pic = (ImageView) findViewById(R.id.car_pic);
            brand = (TextView) findViewById(R.id.brand);
            model = (TextView) findViewById(R.id.model);
            back = (ImageView) findViewById(R.id.back);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Questionnaire.this, MoreActivity.class);
                    intent.putExtra("cars", fcars);
                    startActivity(intent);
                    finish();
                }
            });

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
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    result();// here will call weka classifier
                    progressBar.setVisibility(View.INVISIBLE);
                    loading.setVisibility(View.INVISIBLE);
                    Tran.setVisibility(View.INVISIBLE);
                }
            }, 3000);   //3 seconds
        }

    }

    //----------------------------------------------------------------------------------------------
    int getavg(String s) {
        String arr[] = null;
        arr = s.split("-");
        int sum = 0;
        sum = Integer.parseInt(arr[0]) + Integer.parseInt(arr[1]);
        return sum / 2;
    }

    void result() {
        //grab the data from here
        int price = getavg(answers[0]);
        String country = answers[1];
        String body = answers[2];
        String style = answers[3];
        int power = getavg(answers[4]);
        int topseed = getavg(answers[5]);

        String bestmodel = predict(price, country, body, style, power, topseed);

        // find car company/brand against best model in csv file
        InputStream is = null;
        String company = null;
        String url = null;
        AssetManager assetManager = getAssets();
        try {
            is = assetManager.open("cleandataset2.csv");
            assetManager = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");    // use comma as separator
                if (bestmodel.equalsIgnoreCase("None"))// we didnt any best predict from decision tree, so will suggest a car to customer based on filtering records
                {

                    System.out.println("i m none");
                    if (row[7].contains(style)) {
                        company = row[0];
                        url = row[1];
                        bestmodel = row[2];
                        break;
                    }
                } else {
                    if (row[2].contains(bestmodel)) {
                        company = row[0];
                        url = row[1];
                        break;
                    }
                }
            }
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        brand.setText("Brand: " + company);
        model.setText("Model: " + bestmodel);
        Picasso.with(Questionnaire.this).load(url)
                .resize(450, 350)
                .into(car_pic);


    }

    // initiate weka classifier to predict suitable car based on answers
    // we will pass all the user answers to weka classifier
    public String predict(int price, String country, String body, String style, int power, int speed) {

        final Attribute priceAtt = new Attribute("Price");
        final Attribute bodyAtt = new Attribute("Body", Arrays.asList(DataConstants.body));
        final Attribute styleAtt = new Attribute("Style", Arrays.asList(DataConstants.style));
        final Attribute powerAtt = new Attribute("Power (hp)");
        final Attribute topseedAtt = new Attribute("Top speed (kph)");
        final Attribute countryAtt = new Attribute("Country of Origin", Arrays.asList(DataConstants.country));
        final List<String> classes = new ArrayList<String>() {
            {
                for (int i = 0; i < DataConstants.label.length; i++) {
                    add(DataConstants.label[i]);
                }
            }
        };

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>(2) {
            {
                add(priceAtt);
                add(countryAtt);
                add(bodyAtt);
                add(styleAtt);
                add(powerAtt);
                add(topseedAtt);
                Attribute attributeClass = new Attribute("Model", classes);
                add(attributeClass);
            }
        };
        Instances dataUnpredicted = new Instances("TestInstances",
                attributeList, 1);
        dataUnpredicted.setClassIndex(dataUnpredicted.numAttributes() - 1);

        DenseInstance newInstance = new DenseInstance(dataUnpredicted.numAttributes()) {
            {

                setValue(priceAtt, price);
                setValue(bodyAtt, body);
                setValue(styleAtt, style);
                setValue(powerAtt, power);
                setValue(topseedAtt, speed);
                setValue(countryAtt, country);

            }
        };
        DenseInstance testins = newInstance;
        newInstance.setDataset(dataUnpredicted);
        double result = 0;
        try {
            AssetManager assetManager = getAssets();
            Classifier cls = (Classifier) weka.core.SerializationHelper.read(assetManager.open("model2.model"));
            result = cls.classifyInstance(testins);
            cls = null;
            assetManager = null;
            // assetManager.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("didnt find suitable Car");
            // suggest a car
            return "None";
        }
        return classes.get(new Double(result).intValue());
    }

    // this function load all the cars in the car array list
    public ArrayList<Car> loadCars() {
        ArrayList<Car> list = new ArrayList<>();
        InputStream is = null;
        String company = null;
        String url = null;
        AssetManager assetManager = getAssets();
        try {
            is = assetManager.open("cleandataset2.csv");
            assetManager = null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            br.readLine();// skip headline
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");    // use comma as separator
                Car c = new Car(row[0], row[1], row[2], Integer.parseInt(row[3].trim()), row[4], row[5], row[6], row[7], row[8], row[9],
                        Integer.parseInt(row[10].trim()), Integer.parseInt(row[11].trim()), row[12], row[13], row[14],
                        Double.valueOf(row[15].trim()).intValue());
                list.add(c);
            }
            br.close();
            is.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
