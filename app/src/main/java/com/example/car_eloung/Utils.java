package com.example.car_eloung;

public class Utils
{
    public static String[] range(int start, int end)
    {
        String ranges[] = new String[4];
        int n = 4;
        int total_length = end - start;
        int subrange_length = total_length/n;
        int current_start = start;
        for (int i = 0; i < n; ++i)
        {
            ranges[i] =  current_start + "-"+(current_start + subrange_length);
            //System.out.println(ranges[i]);
            current_start += subrange_length;
        }
        return ranges;
    }
}
