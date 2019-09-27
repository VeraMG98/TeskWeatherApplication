package com.example.teskweatherapplication.ParsJson;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.teskweatherapplication.Activity.MainActivity;
import com.example.teskweatherapplication.Other.ApiKey;
import com.example.teskweatherapplication.Models.Location.example.LocationMainModel;
import com.example.teskweatherapplication.Models.OneHourWeather.Example;
import com.example.teskweatherapplication.Models.WeatherForFiveDays.DailyForecast;
import com.example.teskweatherapplication.Models.WeatherForFiveDays.WeatherForFiveDays;
import com.example.teskweatherapplication.Other.MonthDay;
import com.example.teskweatherapplication.Other.WeatherIcons;
import com.example.teskweatherapplication.Other.WeekDay;
import com.example.teskweatherapplication.Retrofit.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ParsJSON {
    private Context mContext;
    private Activity mActivity;
    public static String cityKey = null;
    public static String cityName = null;
    ApiKey apiKey = new ApiKey();

    public ParsJSON(Context context, Activity activity) {
        this.mContext = context;
        this.mActivity = activity;
        parsLocation();
    }

    public void parsLocation(){
        String retUrl = "" + MainActivity.loc + "%2C" + MainActivity.lng;
        final Call<LocationMainModel> responseLocation = RetrofitClient.getApiService().getCommitsByName("" + apiKey.mid(3),retUrl, "ru-ru");
        responseLocation.enqueue(new Callback<LocationMainModel>() {
            @Override
            public void onResponse(Call<LocationMainModel> call, Response<LocationMainModel> response) {
                if (response.isSuccessful()){
                    LocationMainModel locationResponse = response.body();

                    if (locationResponse != null) {
                        cityKey = locationResponse.getKey();
                    }
                    if (locationResponse != null) {
                        cityName = locationResponse.getEnglishName();
                    }
                    parsWeatherNow(cityKey);
                    MainActivity.getCityName(cityName);
                }
                Log.i("URLLocation", response.raw().request().url() + "");
            }

            @Override
            public void onFailure(Call<LocationMainModel> call, Throwable t) {
                Log.i("URLLocationEr", t.getLocalizedMessage());
            }
        });
    }

    public void parsWeatherNow(final String key_api_){
        final Call<List<Example>> responseWeatherNow = RetrofitClient.getApiService().getCommitsByWeatherNow("" + key_api_, "" + apiKey.mid(2), "ru-ru", "true", "true");
        responseWeatherNow.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (response.isSuccessful()){
                    List<Example> weatherNow = response.body();
                    assert weatherNow != null;
                    MainActivity.getIconWeatherNow(checkIcon(weatherNow.get(0).getWeatherIcon(), mContext));
                    MainActivity.getWeatherDescription(weatherNow.get(0).getIconPhrase());
                    MainActivity.getTemperatureNow(weatherNow.get(0).getTemperature().getValue());
                    MainActivity.getDayToday(parsDateToday(weatherNow.get(0).getDateTime(), mContext));
                    MainActivity.getWindSpeed((int) Double.parseDouble(weatherNow.get(0).getWind().getSpeed().getValue() + ""));
                    MainActivity.getRealTemp((int) Double.parseDouble(weatherNow.get(0).getRealFeelTemperature().getValue() + "" ));
//                    MainActivity.getUF(weatherNow.get(0).getUVIndex());
                    MainActivity.getDav(weatherNow.get(0).getVisibility().getValue());
                    parsWeatherForFiveDays(key_api_);
                }
                Log.i("URLWeatherNow", response.raw().request().url() + "");
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.i("URLWeatherNow", t.getLocalizedMessage());
            }
        });
    }

    private void parsWeatherForFiveDays(String key_api) {
        final Call<WeatherForFiveDays> responseForFiveDays = RetrofitClient.getApiService().getCommitWeatherForFiveDays("" + key_api, "" + apiKey.mid(4), "ru-ru", "true");
        responseForFiveDays.enqueue(new Callback<WeatherForFiveDays>() {
            @Override
            public void onResponse(Call<WeatherForFiveDays> call, Response<WeatherForFiveDays> response) {
                if (response.isSuccessful()) {
                    List<DailyForecast> res = response.body().getDailyForecasts();
                    for (int i = 0; i < 5; i++) {
                        DailyForecast D = res.get(i);
                        switch (i){
                            case 0 :
                                MainActivity.weatherDet(parsDate(D.getDate(), mContext),
                                        checkIconsFiveDays(D.getDay().getIcon(), D.getNight().getIcon(), mContext),
                                        (int) Double.parseDouble(D.getTemperature().getMaximum().getValue() + ""),
                                        (int) Double.parseDouble(D.getTemperature().getMinimum().getValue() + ""));
                                break;
                            case 1 :
                                MainActivity.weatherDet2(parsDate(D.getDate(), mContext),
                                        checkIconsFiveDays(D.getDay().getIcon(), D.getNight().getIcon(), mContext),
                                        (int) Double.parseDouble(D.getTemperature().getMaximum().getValue() + ""),
                                        (int) Double.parseDouble(D.getTemperature().getMinimum().getValue() + ""));
                                break;
                            case 2 :
                                MainActivity.weatherDet3(parsDate(D.getDate(), mContext),
                                        checkIconsFiveDays(D.getDay().getIcon(), D.getNight().getIcon(), mContext),
                                        (int) Double.parseDouble(D.getTemperature().getMaximum().getValue() + ""),
                                        (int) Double.parseDouble(D.getTemperature().getMinimum().getValue() + ""));
                                break;
                            case 3 :
                                MainActivity.weatherDet4(parsDate(D.getDate(), mContext),
                                        checkIconsFiveDays(D.getDay().getIcon(), D.getNight().getIcon(), mContext),
                                        (int) Double.parseDouble(D.getTemperature().getMaximum().getValue() + ""),
                                        (int) Double.parseDouble(D.getTemperature().getMinimum().getValue() + ""));
                                break;
                            case 4 :
                                MainActivity.weatherDet5(parsDate(D.getDate(), mContext),
                                        checkIconsFiveDays(D.getDay().getIcon(), D.getNight().getIcon(), mContext),
                                        (int) Double.parseDouble(D.getTemperature().getMaximum().getValue() + ""),
                                        (int) Double.parseDouble(D.getTemperature().getMinimum().getValue() + ""));
                                break;
                        }
                    }
                }
                Log.i("URLWeatherForFiveDays", response.raw().request().url() + "");
            }

            @Override
            public void onFailure(Call<WeatherForFiveDays> call, Throwable t) {
                Log.i("URLWeatherForFiveDays1", t.getLocalizedMessage());
            }
        });
    }

    public Drawable checkIcon (int icon, Context context) {
        WeatherIcons weatherIcons = new WeatherIcons();
        Drawable iconNow = context.getResources().getDrawable(weatherIcons.getIcon(icon));
        return iconNow;
    }

    public String parsDate (String date, Context context) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String output = null;
        try {
            WeekDay weekDay = new WeekDay();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Objects.requireNonNull(dateFormat.parse(date)));
            int id = calendar.get(Calendar.DAY_OF_WEEK);
            String dayweek = context.getResources().getString(weekDay.id(id));
            output = dayweek ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String parsDateToday (String date, Context context ) {
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

    public Drawable checkIconsFiveDays (int iconDay, int iconNigth, Context context) {
        Calendar currentTime = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("HH");
        String currentTime1 = format.format(currentTime.getTime());
        int timeNow = Integer.parseInt(currentTime1);
        Drawable drawable;
        WeatherIcons weatherIcons = new WeatherIcons();
        if (timeNow > 18 || timeNow < 6){
            drawable = context.getResources().getDrawable(weatherIcons.getIcon(iconNigth));
         } else {
            drawable = context.getResources().getDrawable(weatherIcons.getIcon(iconDay));
        }
        return drawable;
    }
}
