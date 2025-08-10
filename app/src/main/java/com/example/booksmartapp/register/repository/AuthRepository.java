package com.example.booksmartapp.register.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.booksmartapp.models.requests.LoginRequest;
import com.example.booksmartapp.models.requests.RegisterRequest;
import com.example.booksmartapp.models.requests.VerifyRequest;
import com.example.booksmartapp.register.routes.AuthRoutes;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.ErrorResponse;
import com.example.booksmartapp.responses.LoginResponse;
import com.example.booksmartapp.responses.UsuarioResponse;
import com.example.booksmartapp.responses.VerifyResponse;
import com.example.booksmartapp.retrofit.auth_request;
import com.google.gson.Gson;

import java.io.IOException;

public class AuthRepository {
    public Retrofit retrofit;

    void setRetrofit() {
        retrofit = auth_request.getRetrofit();
    }


    public MutableLiveData<ApiResponse<UsuarioResponse>> register(RegisterRequest request) {
        setRetrofit();
        AuthRoutes authRoute = retrofit.create(AuthRoutes.class);
        MutableLiveData<ApiResponse<UsuarioResponse>> result = new MutableLiveData<>();

        authRoute.register(request).enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<ApiResponse<UsuarioResponse>> call, Response<ApiResponse<UsuarioResponse>> response) {
                ApiResponse<UsuarioResponse> apiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(apiResponse);
                } else {
                    result.setValue(apiResponse);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UsuarioResponse>> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<ApiResponse<VerifyResponse>> verifyEmail(int id, VerifyRequest request) {
        setRetrofit();
        AuthRoutes authRoute = retrofit.create(AuthRoutes.class);
        MutableLiveData<ApiResponse<VerifyResponse>> result = new MutableLiveData<>();

        authRoute.verifyEmail(id, request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse<VerifyResponse>> call, Response<ApiResponse<VerifyResponse>> response) {
                ApiResponse<VerifyResponse> apiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(apiResponse);
                } else {
                    result.setValue(apiResponse);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<VerifyResponse>> call, Throwable t) {
                result.setValue(null);
            }
        });
        return result;
    }

    public MutableLiveData<ApiResponse<LoginResponse>> login(LoginRequest request) {
        setRetrofit();
        AuthRoutes authRoute = retrofit.create(AuthRoutes.class);
        MutableLiveData<ApiResponse<LoginResponse>> result = new MutableLiveData<>();

        authRoute.login(request).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                ApiResponse<LoginResponse> apiResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(apiResponse);
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorResponse<?> errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                            ApiResponse<LoginResponse> errorApiResponse = new ApiResponse<>();
                            errorApiResponse.setStatus(errorResponse.getStatus());
                            errorApiResponse.setMsg(errorResponse.getMsg());
                            errorApiResponse.setData(null);

                            result.setValue(errorApiResponse);
                        } catch (Exception e) {
                            e.printStackTrace();
                            result.setValue(null);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

  /*
    public MutableLiveData<ApiResponse<LoginResponse>> login(LoginRequest request) {
        setRetrofit();
        AuthRoutes authRoute = retrofit.create(AuthRoutes.class);
        MutableLiveData<ApiResponse<LoginResponse>> result = new MutableLiveData<>();
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>();

        authRoute.login(request).enqueue(new Callback<ApiResponse<LoginResponse>>() {

            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiResponse.setData(response.body().getData());
                    apiResponse.setMsg(response.body().getMsg());
                    result.setValue(apiResponse);
                }
                else
                    result.setValue(null);
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                    result.setValue(null);
            }
        });

        return result;
    }
     */
}
