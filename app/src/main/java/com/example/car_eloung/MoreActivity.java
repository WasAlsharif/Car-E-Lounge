package com.example.car_eloung;

import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MoreActivity extends ListActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        ArrayList<Car> cars = new ArrayList<Car>();
        cars = (ArrayList<Car>) getIntent().getSerializableExtra("cars");
        // Setting header
        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("List of Cars");

        ListView listView=(ListView)findViewById(android.R.id.list);
        listView.addHeaderView(textView);

        String models[] = new String[cars.size()];
        String company[] = new String[cars.size()];
        String urls[] = new String[cars.size()];

        for(int i=0;i<cars.size();i++)
        {
            models[i] = cars.get(i).getModel();
            company[i] = cars.get(i).getCompany()+", "+cars.get(i).getBody()+" "+cars.get(i).getStyle()+", "+cars.get(i).getCountry();
            urls[i] = cars.get(i).getUrl();
        }

        // For populating list data
        CarList customCountryList = new CarList(this, models, company, urls);
        listView.setAdapter(customCountryList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
             //   Toast.makeText(getApplicationContext(),"You Selected "+countryNames[position-1]+ " as Country",Toast.LENGTH_SHORT).show();
            }
        });
    }
}