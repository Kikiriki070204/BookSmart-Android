package com.example.booksmartapp.select.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.models.Prestamo;
import com.example.booksmartapp.models.Prestamos;
import com.example.booksmartapp.models.Usuario;
import com.example.booksmartapp.models.requests.ChangePassRequest;
import com.example.booksmartapp.models.requests.PrestamosRequest;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.select.BibliotecasRepository.BibliotecasRepository;

import java.util.List;

public class BibliotecasViewModel extends ViewModel {

    private BibliotecasRepository bibliotecasRepository;

    public BibliotecasViewModel(Context context) {
        bibliotecasRepository = new BibliotecasRepository(context);
    }

    public LiveData<ApiResponse<Bibliotecas>> getBibliotecas() {
        return bibliotecasRepository.getBibliotecas();
    }

    public LiveData<ApiResponse<Usuario>> getUsuarioInfo()
    {
        return bibliotecasRepository.getUsuarioInfo();
    }
    public LiveData<ApiResponse<List<Prestamo>>> getPrestamos(int bibliotecaId, int usuarioId) {
        return bibliotecasRepository.getPrestamos(bibliotecaId, usuarioId);
    }

    public LiveData<ApiResponse> cambiarContrasena(ChangePassRequest request)
    {
        return bibliotecasRepository.changePassword(request);
    }

    public LiveData<ApiResponse<Prestamo>> getPrestamo(int idPrestamo)
    {
        return bibliotecasRepository.getPrestamo(idPrestamo);
    }

}
