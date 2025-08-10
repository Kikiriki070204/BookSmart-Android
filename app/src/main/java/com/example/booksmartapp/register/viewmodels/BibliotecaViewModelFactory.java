package com.example.booksmartapp.register.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;

public class BibliotecaViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public BibliotecaViewModelFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BibliotecaViewModelFactory.class)) {
            return (T) new BibliotecasViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
