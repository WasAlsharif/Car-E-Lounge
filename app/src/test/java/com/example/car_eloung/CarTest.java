package com.example.car_eloung;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void Company() {
        Car c = new Car("Toyota","Camry");
        assertEquals("Toyota",c.getCompany());
    }
}




