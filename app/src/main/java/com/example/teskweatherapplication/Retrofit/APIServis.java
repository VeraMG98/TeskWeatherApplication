package com.example.teskweatherapplication.Retrofit;

import com.example.teskweatherapplication.Models.Location.example.LocationMainModel;
import com.example.teskweatherapplication.Models.OneHourWeather.Example;
import com.example.teskweatherapplication.Models.SearchCity.ModelCitySearch;
import com.example.teskweatherapplication.Models.WeatherForFiveDays.DailyForecast;
import com.example.teskweatherapplication.Models.WeatherForFiveDays.WeatherForFiveDays;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServis {

    //http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?apikey=dmAS11u3JtM3HVZBDpAaxAO46t75vGUA&q=42.8790913%2C74.6180361&language=ru-ru
    @GET("locations/v1/cities/geoposition/search")
    Call<LocationMainModel> getCommitsByName(
            @Query(value = "apikey", encoded = true) String key,
            @Query(value = "q", encoded = true) String url,
            @Query("language") String location
    );

    // http://dataservice.accuweather.com/forecasts/v1/hourly/1hour/222844?apikey=dmAS11u3JtM3HVZBDpAaxAO46t75vGUA&language=ru-ru&details=true&metric=true
    @GET("forecasts/v1/hourly/1hour/{city_key}")
    Call<List<Example>> getCommitsByWeatherNow(
            @Path(value = "city_key") String city_key,
            @Query(value = "apikey",encoded = true) String key,
            @Query("language") String language,
            @Query(value = "details") String details,
            @Query(value = "metric") String metric
    );

    //http://dataservice.accuweather.com/forecasts/v1/daily/5day/222844?apikey=TvqP9vtKHsj4bT7eCYDpH8CX0AxfcrTD&language=ru-ru&details=true&metric=true
    @GET("forecasts/v1/daily/5day/{city_key}")
    Call<WeatherForFiveDays> getCommitWeatherForFiveDays(
            @Path(value = "city_key") String city_key,
            @Query(value = "apikey",encoded = true) String key,
            @Query("language") String language,
            @Query(value = "metric") String metric
    );

    //http://dataservice.accuweather.com/locations/v1/search?apikey=Z9kxrC6A4Hsk21i6fxkkYGQ0uoS4uW2w&q=Bishkek
    @GET("locations/v1/cities/search")
    Call<List<ModelCitySearch>> getCommitSearchCity(
            @Query(value = "apikey", encoded = true) String key,
            @Query(value = "q", encoded = true) String name
            );
}
