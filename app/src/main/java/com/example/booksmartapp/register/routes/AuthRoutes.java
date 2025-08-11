package com.example.booksmartapp.register.routes;

import com.example.booksmartapp.models.Usuario;
import com.example.booksmartapp.models.requests.LoginRequest;
import com.example.booksmartapp.models.requests.RegisterRequest;
import com.example.booksmartapp.models.requests.VerifyRequest;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.LoginResponse;
import com.example.booksmartapp.responses.UsuarioResponse;
import com.example.booksmartapp.responses.VerifyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthRoutes {
    @POST("auth/usuario/register")
    Call<ApiResponse<UsuarioResponse>> register(@Body RegisterRequest request);


    @POST("auth/verifyemail/{id}")
    Call<ApiResponse<VerifyResponse>> verifyEmail(@Path("id") int i, @Body VerifyRequest request);


    @POST("auth/login")
    Call<ApiResponse<LoginResponse>> login(@Body LoginRequest request);

    @GET("auth/userinfo")
    Call<ApiResponse<Usuario>> getUserInfo();

}
