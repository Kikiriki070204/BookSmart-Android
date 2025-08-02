package com.example.booksmartapp.register.routes;

import com.example.booksmartapp.register.RegisterRequest;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthRoutes {
    @POST("auth/register")
    Call<ApiResponse<UsuarioResponse>> register(@Body RegisterRequest request);

}
