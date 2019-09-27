package com.example.teskweatherapplication.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.teskweatherapplication.MyLocation;
import com.example.teskweatherapplication.ParsJson.ParsJSON;
import com.example.teskweatherapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static double loc, lng;
    ParsJSON parsJSON;
    public static TextView nameCity, weatherNowDescr, temperatureNow, dateToday, date1, maxtemp1, mintemp1,
            date2, maxtemp2, mintemp2, date3, maxtemp3, mintemp3, date4, maxtemp4, mintemp4, date5, maxtemp5, mintemp5,
            windspeed, realtemp, dav, uff, osh, sped, uf, davlenie;
    public static ImageView iconWeatherNow;
    RelativeLayout linearLayout;
    public static ImageView imageView, icon1, icon2, icon3, icon4, icon5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        backGround();
        onClickSearchButton();
        parsJSON = new ParsJSON(this, this);
        checkPermissionLocation();
        parsJsonLocationCityApiKey();
    }

    private void onClickSearchButton() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
    }

    private void showInputDialog() {
        Intent intent = new Intent(MainActivity.this, SearchDialog.class);
        startActivity(intent);
    }

    private void initComponents() {
        nameCity = findViewById(R.id.city_name);
        weatherNowDescr = findViewById(R.id.id_temperature_description);
        temperatureNow = findViewById(R.id.id_temperature_now);
        iconWeatherNow = findViewById(R.id.icon_weather_now);
        dateToday = findViewById(R.id.id_date);
        linearLayout = findViewById(R.id.main_linear_l);
        imageView = findViewById(R.id.search_but);
        windspeed = findViewById(R.id.wind_speed);
        realtemp = findViewById(R.id.real_temp);
        sped = findViewById(R.id.sped);
        osh = findViewById(R.id.osh);
        dav = findViewById(R.id.dav);
        uff = findViewById(R.id.uff);
        uf = findViewById(R.id.uf);
        davlenie = findViewById(R.id.davlenie);

        date1 = findViewById(R.id.day1);
        maxtemp1= findViewById(R.id.max_temp1);
        mintemp1 = findViewById(R.id.min_temp1);
        icon1 = findViewById(R.id.icon1);
        date2 = findViewById(R.id.day2);
        maxtemp2= findViewById(R.id.max_temp2);
        mintemp2 = findViewById(R.id.min_temp2);
        icon2 = findViewById(R.id.icon2);
        date3 = findViewById(R.id.day3);
        maxtemp3= findViewById(R.id.max_temp3);
        mintemp3 = findViewById(R.id.min_temp3);
        icon3 = findViewById(R.id.icon3);
        date4 = findViewById(R.id.day4);
        maxtemp4= findViewById(R.id.max_temp4);
        mintemp4 = findViewById(R.id.min_temp4);
        icon4 = findViewById(R.id.icon4);
        date5 = findViewById(R.id.day5);
        maxtemp5= findViewById(R.id.max_temp5);
        mintemp5 = findViewById(R.id.min_temp5);
        icon5 = findViewById(R.id.icon5);
    }

    private void checkPermissionLocation() {
        if (ContextCompat.checkSelfPermission( MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( MainActivity.this ,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        MyLocation myLocation = new MyLocation(this, this);
        loc = myLocation.getLatitude ();
        lng = myLocation.getLongitude ();
    }

    public void parsJsonLocationCityApiKey(){
        parsJSON.parsLocation();
    }

    public static void getCityName(String string) {
        nameCity.setText(string);
    }

    public static void getWeatherDescription(String description) {
        weatherNowDescr.setText(description);
    }

    public static void getTemperatureNow(double temp) {
        temperatureNow.setText(((int)temp) + "°");
    }

    public static void getIconWeatherNow(Drawable drawable) {
        iconWeatherNow.setImageDrawable(drawable);
    }

    public static void getDayToday(String date) {
        dateToday.setText(date);
    }

    public static void getWindSpeed(int speed) {
        windspeed.setText(speed + " км/ч");
    }

    public static void getRealTemp (double temp) {
        realtemp.setText((int) temp + "°");
    }

    public static void getUF(int indexUf) {
        uf.setText(indexUf);
    }

    public static void getDav(double dav) {
        davlenie.setText((int)dav + " км");
    }

    public  static void weatherDet(String date, Drawable drawable, double maxtemp, double mintemp){
        date1.setText(date);
        icon1.setImageDrawable(drawable);
        maxtemp1.setText((int)maxtemp + "°");
        mintemp1.setText((int)mintemp + "°");
    }

    public  static void weatherDet2(String date, Drawable drawable, double maxtemp, double mintemp){
        date2.setText(date);
        icon2.setImageDrawable(drawable);
        maxtemp2.setText((int)maxtemp + "°");
        mintemp2.setText((int)mintemp + "°");
    }

    public  static void weatherDet3(String date, Drawable drawable, double maxtemp, double mintemp){
        date3.setText(date);
        icon3.setImageDrawable(drawable);
        maxtemp3.setText((int)maxtemp + "°");
        mintemp3.setText((int)mintemp + "°");
    }

    public  static void weatherDet4(String date, Drawable drawable, double maxtemp, double mintemp){
        date4.setText(date);
        icon4.setImageDrawable(drawable);
        maxtemp4.setText((int)maxtemp + "°");
        mintemp4.setText((int)mintemp + "°");
    }

    public  static void weatherDet5(String date, Drawable drawable, double maxtemp, double mintemp){
        date5.setText(date);
        icon5.setImageDrawable(drawable);
        maxtemp5.setText((int)maxtemp + "°");
        mintemp5.setText((int)mintemp + "°");
    }

    public void backGround() {
        Calendar currentTime = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("HH");
        String currentTime1 = format.format(currentTime.getTime());
        int timeNow = Integer.parseInt(currentTime1);
        if (timeNow > 18 || timeNow < 6 ){
            linearLayout.setBackground(getResources().getDrawable(R.drawable.nigth2));
            temperatureNow.setTextColor(getResources().getColor(R.color.colorNigth));
            nameCity.setTextColor(getResources().getColor(R.color.colorNigth));
            weatherNowDescr.setTextColor(getResources().getColor(R.color.colorNigth));
            dateToday.setTextColor(getResources().getColor(R.color.colorNigth));
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_add));
            date1.setTextColor(getResources().getColor(R.color.colorNigth));
            date2.setTextColor(getResources().getColor(R.color.colorNigth));
            date3.setTextColor(getResources().getColor(R.color.colorNigth));
            date4.setTextColor(getResources().getColor(R.color.colorNigth));
            date5.setTextColor(getResources().getColor(R.color.colorNigth));
            maxtemp1.setTextColor(getResources().getColor(R.color.colorNigth));
            maxtemp2.setTextColor(getResources().getColor(R.color.colorNigth));
            maxtemp3.setTextColor(getResources().getColor(R.color.colorNigth));
            maxtemp4.setTextColor(getResources().getColor(R.color.colorNigth));
            maxtemp5.setTextColor(getResources().getColor(R.color.colorNigth));
            mintemp1.setTextColor(getResources().getColor(R.color.colorNigth));
            mintemp2.setTextColor(getResources().getColor(R.color.colorNigth));
            mintemp3.setTextColor(getResources().getColor(R.color.colorNigth));
            mintemp4.setTextColor(getResources().getColor(R.color.colorNigth));
            mintemp5.setTextColor(getResources().getColor(R.color.colorNigth));
            realtemp.setTextColor(getResources().getColor(R.color.colorNigth));
            windspeed.setTextColor(getResources().getColor(R.color.colorNigth));
            uff.setTextColor(getResources().getColor(R.color.colorNigth));
            dav.setTextColor(getResources().getColor(R.color.colorNigth));
            osh.setTextColor(getResources().getColor(R.color.colorNigth));
            sped.setTextColor(getResources().getColor(R.color.colorNigth));
            davlenie.setTextColor(getResources().getColor(R.color.colorNigth));
        } else {
            linearLayout.setBackground(getResources().getDrawable(R.drawable.morning));
            temperatureNow.setTextColor(getResources().getColor(R.color.colorDay));
            nameCity.setTextColor(getResources().getColor(R.color.colorDay));
            weatherNowDescr.setTextColor(getResources().getColor(R.color.colorDay));
            dateToday.setTextColor(getResources().getColor(R.color.colorDay));
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_day));
            date1.setTextColor(getResources().getColor(R.color.colorDay));
            date2.setTextColor(getResources().getColor(R.color.colorDay));
            date3.setTextColor(getResources().getColor(R.color.colorDay));
            date4.setTextColor(getResources().getColor(R.color.colorDay));
            date5.setTextColor(getResources().getColor(R.color.colorDay));
            maxtemp1.setTextColor(getResources().getColor(R.color.colorDay));
            maxtemp2.setTextColor(getResources().getColor(R.color.colorDay));
            maxtemp3.setTextColor(getResources().getColor(R.color.colorDay));
            maxtemp4.setTextColor(getResources().getColor(R.color.colorDay));
            maxtemp5.setTextColor(getResources().getColor(R.color.colorDay));
            mintemp1.setTextColor(getResources().getColor(R.color.colorDay));
            mintemp2.setTextColor(getResources().getColor(R.color.colorDay));
            mintemp3.setTextColor(getResources().getColor(R.color.colorDay));
            mintemp4.setTextColor(getResources().getColor(R.color.colorDay));
            mintemp5.setTextColor(getResources().getColor(R.color.colorDay));
            realtemp.setTextColor(getResources().getColor(R.color.colorDay));
            windspeed.setTextColor(getResources().getColor(R.color.colorDay));
            uff.setTextColor(getResources().getColor(R.color.colorDay));
            dav.setTextColor(getResources().getColor(R.color.colorDay));
            osh.setTextColor(getResources().getColor(R.color.colorDay));
            sped.setTextColor(getResources().getColor(R.color.colorDay));
            davlenie.setTextColor(getResources().getColor(R.color.colorDay));

        }
    }
}