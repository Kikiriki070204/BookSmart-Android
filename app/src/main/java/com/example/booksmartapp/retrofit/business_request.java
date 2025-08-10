package com.example.booksmartapp.retrofit;

import com.example.booksmartapp.interceptor.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class business_request {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(String token) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(token))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.8:3334/api/business/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
