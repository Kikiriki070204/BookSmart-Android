package com.example.booksmartapp.register.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.booksmartapp.models.requests.RegisterRequest;
import com.example.booksmartapp.models.requests.VerifyRequest;
import com.example.booksmartapp.register.repository.AuthRepository;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.UsuarioResponse;

public class AuthViewModel extends ViewModel {
    private AuthRepository authRepository = new AuthRepository();

    public LiveData<UsuarioResponse> getRegister(RegisterRequest request) {
        return authRepository.register(request);
    }

    public LiveData<ApiResponse<Void>> getVerify(int id, VerifyRequest verifyRequest)
    {
     return authRepository.verify(id, verifyRequest);
    }
}
