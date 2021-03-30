package com.example.car_eloung;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CarList extends ArrayAdapter {
    private String[] models;
    private String[] companies;
    private String[] imageid;
    private Activity context;

    public CarList(Activity context, String[] models, String[] companies, String[] url) {
        super(context, R.layout.more_item, models);
        this.context = context;
        this.models = models;
        this.companies = companies;
        this.imageid = url;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.more_item, null, true);
        TextView textViewCountry = (TextView) row.findViewById(R.id.textViewModel);
        TextView textViewCapital = (TextView) row.findViewById(R.id.textViewCompany);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);

        textViewCountry.setText(models[position]);
        textViewCapital.setText(companies[position]);
        Picasso.with(context).load(imageid[position]).into(imageFlag);
        return  row;
    }
}