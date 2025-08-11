package com.example.booksmartapp.retrofit;

import com.example.booksmartapp.interceptor.AuthInterceptor;
import com.example.booksmartapp.models.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class auth_request {
    //este es un endpoint temporal
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + Constants.BASE_IP+ ":3333/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
