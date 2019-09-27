package com.example.teskweatherapplication.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teskweatherapplication.Models.FiveDays;
import com.example.teskweatherapplication.R;
import com.example.teskweatherapplication.Other.WeatherIcons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class WeatherAdapter extends ArrayAdapter<FiveDays> {
    public WeatherAdapter(Context context, List<FiveDays> objects){
        super(context, 0, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FiveDays fiveDays = getItem(position);
        WeatherIcons weatherIcons = new WeatherIcons();
        Context context;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        context = convertView.getContext();
        TextView dateTextView = convertView.findViewById(R.id.day_weak);
        TextView nithTempTextView = convertView.findViewById(R.id.wMinTemperature);
        dateTextView.setText(fiveDays.getDate());
        nithTempTextView.setText(fiveDays.getTemperature());

        int idD = Integer.parseInt(fiveDays.getIcon().toString());
        ImageView iconDayW = convertView.findViewById(R.id.dayIcon);
        Drawable day = context.getResources().getDrawable(weatherIcons.getIcon(idD));
        iconDayW.setImageDrawable(day);

        Calendar currentTime = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("HH");
        String currentTime1 = format.format(currentTime.getTime());
        int timeNow = Integer.parseInt(currentTime1);

        if (timeNow > 18 || timeNow < 6) {
            dateTextView.setTextColor(context.getResources().getColor(R.color.colorNigth));
            nithTempTextView.setTextColor(context.getResources().getColor(R.color.colorNigth));
        } else {
            dateTextView.setTextColor(context.getResources().getColor(R.color.colorDay));
            nithTempTextView.setTextColor(context.getResources().getColor(R.color.colorDay));
        }

        return convertView;
    }
}
