package com.example.booksmartapp.register.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.booksmartapp.models.requests.LoginRequest;
import com.example.booksmartapp.models.requests.RegisterRequest;
import com.example.booksmartapp.models.requests.VerifyRequest;
import com.example.booksmartapp.register.repository.AuthRepository;
import com.example.booksmartapp.responses.ApiResponse;
import com.example.booksmartapp.responses.LoginResponse;
import com.example.booksmartapp.responses.UsuarioResponse;
import com.example.booksmartapp.responses.VerifyResponse;

public class AuthViewModel extends ViewModel {
    private AuthRepository authRepository = new AuthRepository();

    public LiveData<ApiResponse<UsuarioResponse>> getRegister(RegisterRequest request) {
        return authRepository.register(request);
    }

    public LiveData<ApiResponse<VerifyResponse>> getVerify(int id, VerifyRequest verifyRequest)
    {
     return authRepository.verifyEmail(id, verifyRequest);
    }

    public LiveData<ApiResponse<LoginResponse>> getLogin(LoginRequest request) {
        return authRepository.login(request);
    }
}
