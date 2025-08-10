package com.example.booksmartapp.models;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class SessionManager {
    private static SessionManager instance;

    private List<Biblioteca> bibliotecas;

    private int bibliotecaSeleccionadaId;
    private Usuario.UsuarioLogin usuario;
    private String token;

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    private String contrasena;

    public List<Biblioteca> getBibliotecas() {
        return bibliotecas;
    }

    public void setBibliotecas(List<Biblioteca> bibliotecas) {
        this.bibliotecas = bibliotecas;
    }

    public int getBibliotecaSeleccionadaId() {
        return bibliotecaSeleccionadaId;
    }

    public void setBibliotecaSeleccionadaId(int bibliotecaSeleccionadaId) {
        this.bibliotecaSeleccionadaId = bibliotecaSeleccionadaId;
    }

    public Usuario.UsuarioLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario.UsuarioLogin usuario) {
        this.usuario = usuario;
    }

    public SessionManager() {
        // Constructor privado
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }


    public void saveToken(Context context, String token) {
        this.token = token;
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken(Context context) {
        if (token == null) {
            SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            token = prefs.getString("token", null);
        }
        return token;
    }
}