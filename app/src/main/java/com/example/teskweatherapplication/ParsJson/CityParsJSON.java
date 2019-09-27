package com.example.teskweatherapplication.ParsJson;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.teskweatherapplication.Other.ApiKey;
import com.example.teskweatherapplication.Models.OneHourWeather.Example;
import com.example.teskweatherapplication.Models.SearchCity.ModelCitySearch;
import com.example.teskweatherapplication.Other.MonthDay;
import com.example.teskweatherapplication.Other.WeatherIcons;
import com.example.teskweatherapplication.Other.WeekDay;
import com.example.teskweatherapplication.Retrofit.RetrofitClient;
import com.example.teskweatherapplication.Activity.SearchDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityParsJSON {

    private Context mContext;
    private Activity mActivity;
    public static String cityKey = null;
    public static String name = null;
    ApiKey apiKey = new ApiKey();


    public CityParsJSON (Context context, Activity activity) {
        this.mActivity = activity;
        this.mContext = context;
        parsCityNew();
    }

    public static void getNameCityes(String string) {
        name = string;
    }

    public void parsCityNew() {
        final Call<List<ModelCitySearch>> responseCitySearch = RetrofitClient.getApiService().getCommitSearchCity("" + apiKey.mid(0), "" + name);
        responseCitySearch.enqueue(new Callback<List<ModelCitySearch>>() {
            @Override
            public void onResponse(Call<List<ModelCitySearch>> call, Response<List<ModelCitySearch>> response) {
                if (response.isSuccessful()) {
                   List<ModelCitySearch> modelCitySearch = response.body();
                    cityKey = modelCitySearch.get(0).getKey();
                    SearchDialog.getCityName(modelCitySearch.get(0).getEnglishName() + ", " + modelCitySearch.get(0).getAdministrativeArea().getCountryID());
                    parsWeatherNow(cityKey);
                }
                Log.i("URLLocation", response.raw().request().url() + "");
            }

            @Override
            public void onFailure(Call<List<ModelCitySearch>> call, Throwable t) {
                Log.i("URLLocationEr", t.getLocalizedMessage());
            }
        });
    }

    public void parsWeatherNow(String cityKey) {
        final Call<List<Example>> responseWeatherNow = RetrofitClient.getApiService().getCommitsByWeatherNow("" + cityKey, "" + apiKey.mid(1), "ru-ru", "true", "true");
        responseWeatherNow.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (response.isSuccessful()){
                    List<Example> weatherNow = response.body();
                    assert weatherNow != null;
                    SearchDialog.getIconWeatherNow(checkIcon(weatherNow.get(0).getWeatherIcon(), mContext));
                    SearchDialog.getWeatherDescription(weatherNow.get(0).getIconPhrase());
                    SearchDialog.getTemperatureNow(weatherNow.get(0).getTemperature().getValue());
                    SearchDialog.getDayToday(parsDateToday(weatherNow.get(0).getDateTime(), mContext));
                    SearchDialog.getWindSpeed((int) Double.parseDouble(weatherNow.get(0).getWind().getSpeed().getValue() + ""));
                    SearchDialog.getRealTemp((int) Double.parseDouble(weatherNow.get(0).getRealFeelTemperature().getValue() + "" ));
//                    MainActivity.getUF(weatherNow.get(0).getUVIndex());
                    SearchDialog.getDav(weatherNow.get(0).getVisibility().getValue());

                }
                Log.i("URLWeatherNow", response.raw().request().url() + "");
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.i("URLWeatherNow", t.getLocalizedMessage());
            }
        });
    }

    public  Drawable checkIcon(int icon, Context context) {
        WeatherIcons weatherIcons = new WeatherIcons();
        Drawable iconNow = context.getResources().getDrawable(weatherIcons.getIcon(icon));
        return iconNow;
    }

    public  String parsDateToday(String date, Context context) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String output = null;
        try {
            WeekDay weekDay = new WeekDay();
            MonthDay monthWeek = new MonthDay();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Objects.requireNonNull(dateFormat.parse(date)));
            int id = calendar.get(Calendar.DAY_OF_WEEK);
            String dayweek = context.getResources().getString(weekDay.id(id));
            int idm = calendar.get(Calendar.MONTH);
            String monthWeek1 = context.getResources().getString(monthWeek.mid(idm));
            output = dayweek + ", " + calendar.get(Calendar.DAY_OF_MONTH) + " " + monthWeek1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }
}
