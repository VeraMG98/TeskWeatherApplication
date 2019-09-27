package com.example.teskweatherapplication.Other;

import com.example.teskweatherapplication.R;

public class WeekDay {
    public int id(int number) {
        int[] wDay = new int[]{
                0,
                R.string.sunday,
                R.string.monday,
                R.string.tuesday,
                R.string.wednesday,
                R.string.thursday,
                R.string.friday,
                R.string.saturday
        };
        return wDay[number];
    }
}
