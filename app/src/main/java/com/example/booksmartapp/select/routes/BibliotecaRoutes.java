package com.example.booksmartapp.select.routes;

import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.responses.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BibliotecaRoutes {
    @GET("bibliotecas/usuario/{id}")
    Call<ApiResponse<Bibliotecas>> getBibliotecas(@Path("id") int i);
}
