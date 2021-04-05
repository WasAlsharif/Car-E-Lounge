package com.example.car_eloung;

import java.io.Serializable;

public class Car implements Serializable {
    private String company;
    private String url;
    private int price;
    private String model;
    private String country;
    private String class_;
    private String body;
    private String style;
    private String engine;
    private String gearbox;
    private int powerhp;
    private int torque;
    private String fueleconl;
    private String fueleconk;
    private String kph;
    private int topspeed;

    public Car(String company, String url, String model, int price, String country, String class_, String body, String style, String engine,
               String gearbox, int powerhp, int torque, String fueleconl, String fueleconk, String kph, int topspeed) {
        this.company = company;
        this.url = url;
        this.price = price;
        this.model = model;
        this.country = country;
        this.class_ = class_;
        this.body = body;
        this.style = style;
        this.engine = engine;
        this.gearbox = gearbox;
        this.powerhp = powerhp;
        this.torque = torque;
        this.fueleconl = fueleconl;
        this.fueleconk = fueleconk;
        this.kph = kph;
        this.topspeed = topspeed;
    }
    public Car(String company,String model) {
        this.company = company;
        this.model = model;
    }

    public String getEngine() {
        return this.engine;
    }

    public String getGearbox() {
        return this.gearbox;
    }

    public int getPowerhp() {
        return this.powerhp;
    }

    public int getTorque() {
        return this.torque;
    }

    public String getFueleconl() {
        return this.fueleconl;
    }

    public String getFueleconk() {
        return this.fueleconk;
    }

    public String getKph() {
        return this.kph;
    }

    public int getTopspeed() {
        return this.topspeed;
    }



    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return this.url;
    }

    public int getPrice() {
        return this.price;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCountry() {
        return this.country;
    }

    public String getClass_() {
        return this.class_;
    }

    public String getBody() {
        return this.body;
    }

    public String getStyle() {
        return this.style;
    }

}
