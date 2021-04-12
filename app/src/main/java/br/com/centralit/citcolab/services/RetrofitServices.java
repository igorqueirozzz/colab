package br.com.centralit.citcolab.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {

    public static Retrofit getRetrofitService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        String API = "http://10.100.0.77:8080";
        return new Retrofit
                .Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

}
