package com.example.teskweatherapplication.Activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teskweatherapplication.ParsJson.CityParsJSON;
import com.example.teskweatherapplication.R;

public class SearchDialog extends AppCompatActivity {
    EditText searchEdit;
    ImageView searchButtonCity;
    public static TextView nameCity, weatherNowDescr, temperatureNow, dateToday, textView, textView1, textView2;
    public static ImageView iconWeatherNow;
    LinearLayout linearLayout;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dialog);
        initComponents();
        onClickBtnSearch();

    }

    private void onClickBtnSearch() {
        searchButtonCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchDialog.this, "" + searchEdit.getText(), Toast.LENGTH_SHORT).show();
                name = searchEdit.getText().toString();
                parser(name);
                searchEdit.getText().clear();
            }
        });
    }

    private void parser(String nameCity) {
        CityParsJSON.getNameCityes(nameCity);
        CityParsJSON cityParsJSON = new CityParsJSON(this, this);
        cityParsJSON.parsCityNew();
    }

    private void initComponents() {
        searchEdit = findViewById(R.id.add_city);
        searchButtonCity = findViewById(R.id.search_city_btn);
        nameCity = findViewById(R.id.city_name_city);
        weatherNowDescr = findViewById(R.id.id_temperature_description_city);
        temperatureNow = findViewById(R.id.id_temperature_now_city);
        iconWeatherNow = findViewById(R.id.icon_weather_now_city);
        dateToday = findViewById(R.id.id_date_city);
        linearLayout = findViewById(R.id.main_linear_l_city);
        textView = findViewById(R.id.wind_speedd);
        textView1 = findViewById(R.id.davleniee);
        textView2 = findViewById(R.id.real_tempp);

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

    public static void getWindSpeed(int parseDouble) {
        textView.setText(parseDouble + " км/ч");
    }

    public static void getRealTemp(int parseDouble) {
        textView2.setText(parseDouble + "°");
    }

    public static void getDav(Double value) {
        textView1.setText(value + " км");
    }
}
