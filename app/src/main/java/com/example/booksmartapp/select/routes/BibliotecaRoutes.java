package com.example.booksmartapp.select.routes;

import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.models.Prestamo;
import com.example.booksmartapp.models.Prestamos;
import com.example.booksmartapp.models.requests.PrestamosRequest;
import com.example.booksmartapp.responses.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BibliotecaRoutes {
    @GET("bibliotecas/usuario/{id}")
    Call<ApiResponse<Bibliotecas>> getBibliotecas(@Path("id") int i);

    @GET("prestamos/biblioteca/{bibliotecaId}/{usuarioId}")
    Call<ApiResponse<Prestamos>> getPrestamosByBiblioteca(@Path("bibliotecaId") int bibliotecaId,
                                                           @Path("usuarioId") int usuarioId);
    @GET("prestamo/{id}")
    Call<ApiResponse<Prestamo>> getPrestamoById(@Path("id") int prestamoId);

}
