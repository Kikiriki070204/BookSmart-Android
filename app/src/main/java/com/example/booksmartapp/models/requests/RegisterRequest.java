package com.example.booksmartapp.models.requests;

public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String celular;
    private String genero;

    public RegisterRequest(String nombre, String apellido, String correo, String celular, String contraseña, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.celular = celular;
        this.genero = genero;
    }

}
