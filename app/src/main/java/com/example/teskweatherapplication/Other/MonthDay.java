package com.example.teskweatherapplication.Other;

import com.example.teskweatherapplication.R;

public class MonthDay {
    public int mid(int number){
        int[] wMonth = new int[]{
                R.string.january,
                R.string.february,
                R.string.match,
                R.string.april,
                R.string.may,
                R.string.june,
                R.string.july,
                R.string.august,
                R.string.september,
                R.string.october,
                R.string.november,
                R.string.december

        };
        return wMonth[number];
    }
}
