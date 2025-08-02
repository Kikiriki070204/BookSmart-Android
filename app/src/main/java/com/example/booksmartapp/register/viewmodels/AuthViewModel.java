package com.example.booksmartapp.register.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.booksmartapp.models.Usuario;
import com.example.booksmartapp.register.RegisterRequest;
import com.example.booksmartapp.register.repository.RegisterRepository;

public class AuthViewModel extends ViewModel {
    private MutableLiveData<Usuario> registerResult = new MutableLiveData<>();
    private RegisterRepository registerRepository;

    public LiveData<Usuario> getRegister(RegisterRequest request) {

        if (registerRepository == null) {
            registerRepository = new RegisterRepository();
        }
        return registerRepository.register(request);

    }

    private void Register(RegisterRequest request)
    {
        if(registerRepository==null)
        {
            registerRepository=new RegisterRepository();
        }
        registerResult = registerRepository.register(request);
    }
}
