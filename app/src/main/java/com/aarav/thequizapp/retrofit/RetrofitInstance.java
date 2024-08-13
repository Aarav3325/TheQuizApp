package com.aarav.thequizapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    String BASE_URL = "http://10.0.2.2/quiz/"; //Special lookup address for accessing host in emulator

    public Retrofit getRetrofit(){
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
