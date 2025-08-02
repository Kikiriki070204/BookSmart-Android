package com.example.booksmartapp.register;

public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String celular;

    public RegisterRequest(String nombre, String apellido, String correo, String celular, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.celular = celular;
    }

}
