package com.example.booksmartapp.select.routes;

import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.responses.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BibliotecaRoutes {
    @GET("bibliotecas")
    Call<ApiResponse<Bibliotecas>> getBibliotecas();
}
