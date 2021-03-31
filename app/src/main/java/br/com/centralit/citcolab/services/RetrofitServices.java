package br.com.centralit.citcolab.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {

    public static Retrofit getRetrofitService(){

        String API = "http://10.100.0.77:8080";
        return new Retrofit
                .Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
