package com.example.teskweatherapplication.Retrofit;

import com.example.teskweatherapplication.Retrofit.APIServis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String URL = "http://dataservice.accuweather.com/";

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder ()
                .baseUrl ( URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
    }

    public static APIServis getApiService() {
        return getRetrofitInstance ().create ( APIServis.class );
    }
}
