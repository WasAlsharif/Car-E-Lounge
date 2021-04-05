package com.example.car_eloung;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompareTest {

    @Test
    public void getAvgTest() {
        Questionnaire q = new Questionnaire();
        int x = q.getavg("84900-99900");
        assertEquals(92400, x);
    }
}




