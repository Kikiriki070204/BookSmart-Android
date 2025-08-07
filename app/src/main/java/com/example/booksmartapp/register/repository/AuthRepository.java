package com.example.booksmartapp.register.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.booksmartapp.models.Usuario;
import com.example.booksmartapp.models.requests.LoginRequest;
import com.example.booksmartapp.models.requests.RegisterRequest;
import com.example.booksmartapp.models.requests.VerifyRequest;
import com.example.booksmartapp.register.routes.AuthRoutes;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.UsuarioResponse;
import com.example.booksmartapp.retrofit.auth_request;
import com.google.gson.Gson;

public class AuthRepository {
    public Retrofit retrofit;

    void setRetrofit() {
        retrofit = auth_request.getRetrofit();
    }

    public MutableLiveData<UsuarioResponse> register(RegisterRequest request) {
        setRetrofit();
        AuthRoutes authRoute = retrofit.create(AuthRoutes.class);
        MutableLiveData<UsuarioResponse> result = new MutableLiveData<>();

        authRoute.register(request).enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<ApiResponse<UsuarioResponse>> call, Response<ApiResponse<UsuarioResponse>> response) {
                Log.d("API", "Código: " + response.code());
                Log.d("API", "Body completo: " + new Gson().toJson(response.body()));
                Log.d("API", "Data: " + new Gson().toJson(response.body().getData()));
                if (response.isSuccessful() && response.body() != null) {
                    UsuarioResponse usuario = response.body().getData();
                    result.setValue(usuario);
                } else {
                    result.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UsuarioResponse>> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<ApiResponse<Void>> verify(int id, VerifyRequest request) {
        setRetrofit();
        AuthRoutes authRoute = retrofit.create(AuthRoutes.class);
        MutableLiveData<ApiResponse<Void>> result = new MutableLiveData<>();
        ApiResponse<Void> apiResponse = new ApiResponse<>();

        authRoute.verifyEmail(id,request).enqueue(new Callback<ApiResponse<Void>>() {

            @Override
            public void onResponse(Call<ApiResponse<Void>> call, Response<ApiResponse<Void>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiResponse.setData(null);
                    apiResponse.setMsg(response.body().getMsg());
                    result.setValue(apiResponse);
                } else {
                    ApiResponse<Void> apiResponse = new ApiResponse<>();
                    apiResponse.setMsg(response.message());
                    result.setValue(apiResponse);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Void>> call, Throwable t) {
                ApiResponse<Void> apiResponse = new ApiResponse<>();
                apiResponse.setMsg(t.getMessage());
                apiResponse.setData(null);
                result.setValue(apiResponse);
            }
        });

        return result;
    }

}
