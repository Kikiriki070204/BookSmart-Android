package com.example.booksmartapp.models;

public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String celular;
    private String genero;

    public RegisterRequest(String nombre, String apellido, String correo, String celular, String contrasena, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.celular = celular;
        this.genero = genero;
    }

}
