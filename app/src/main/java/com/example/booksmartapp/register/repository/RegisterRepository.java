package com.example.booksmartapp.register.repository;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.booksmartapp.models.Usuario;
import com.example.booksmartapp.register.RegisterRequest;
import com.example.booksmartapp.register.routes.AuthRoutes;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.UsuarioResponse;
import com.example.booksmartapp.retrofit.auth_request;

public class RegisterRepository {
    public Retrofit retrofit;

    void setRetrofit() {
        retrofit = auth_request.getRetrofit();
    }

    public MutableLiveData<Usuario> register(RegisterRequest request) {
        setRetrofit();
       AuthRoutes authRoute = retrofit.create(AuthRoutes.class);
        Call<ApiResponse<UsuarioResponse>> registerCall = authRoute.register(request);

        MutableLiveData<Usuario> mutable = new MutableLiveData<>();

        registerCall.enqueue(new Callback<ApiResponse<UsuarioResponse>>() {

            @Override
            public void onResponse(Call<ApiResponse<UsuarioResponse>> call, Response<ApiResponse<UsuarioResponse>> response) {



            }

            @Override
            public void onFailure(Call<ApiResponse<UsuarioResponse>> call, Throwable t) {

            }
        });

        return mutable;
    }

}
