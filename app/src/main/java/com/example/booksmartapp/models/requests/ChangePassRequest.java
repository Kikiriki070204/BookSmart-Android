package com.example.booksmartapp.models.requests;

public class ChangePassRequest {
    private String contraseña_actual;
    private String nueva_contraseña;

    public ChangePassRequest(String contraseña_actual, String nueva_contraseña) {
        this.contraseña_actual = contraseña_actual;
        this.nueva_contraseña = nueva_contraseña;
    }
}
